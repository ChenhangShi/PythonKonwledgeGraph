package com.group2.service;

import com.group2.entities.CommonResult;
import com.group2.entity.node.Answer;
import com.group2.entity.node.Question;
import com.group2.entity.node.Tag;
import com.group2.entity.node.User;
import com.group2.repository.node.AnswerRepository;
import com.group2.repository.node.QuestionRepository;
import com.group2.repository.node.TagRepository;
import com.group2.repository.node.UserRepository;
import com.group2.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RefreshScope
public class RecommendationService {
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserRepository userRepository;

    @Value("${question.vote.min}")
    private Integer questionMinVote;
    @Value("${question.vote.max}")
    private Integer questionMaxVote;
    @Value("${question.viewed-times.min}")
    private Integer questionMinViewedTimes;
    @Value("${question.viewed-times.max}")
    private Integer questionMaxViewedTimes;
    @Value("${question.answer-num.min}")
    private Integer questionMinAnswerNum;
    @Value("${question.answer-num.max}")
    private Integer questionMaxAnswerNum;
    @Value("${question.length.min}")
    private Integer questionMinLength;
    @Value("${question.length.max}")
    private Integer questionMaxLength;

    private final static Integer WEAKEN_FACTOR = 3;

    public CommonResult<List<User>> getRecommendedUsers(String input){
        input = preHandleInput(input);
        Tag tag = getMostSimilarTag(input);
        if(tag==null)
            return new CommonResult<>(404,"抱歉，数据库中没有该领域的知识，请检查拼写或换一种表达方式");
        Map<User,Double> userRecommendScoreMap = getUserRecommendScore(tag);
        List<Map.Entry<User,Double>> entryList = new ArrayList<>(userRecommendScoreMap.entrySet());
        entryList.sort(Map.Entry.comparingByValue());
        List<User> recommendedUsers = new ArrayList<>();
        for (int i = entryList.size()-1; i >= 0 && recommendedUsers.size() < 10 ; i--)
            recommendedUsers.add(entryList.get(i).getKey());
        CommonResult<List<User>> result = new CommonResult<>(200,"成功，推荐顺序从高到低");
        result.setData(recommendedUsers);
        return result;
    }

    private String preHandleInput(String input){
        input = input.trim().toLowerCase();
        input = input.replaceAll("\\s+","-");
        return input;
    }

    private Tag getMostSimilarTag(String input){
        Set<Tag> possibleTags = tagRepository.findPossibleTagsByName(input);
        if(possibleTags.size()==0)
            return null;
        Map<Tag,Integer> editDistanceMap = new HashMap<>();
        for(Tag tag:possibleTags){
            editDistanceMap.put(tag, Utils.editDistance(input,tag.getName()));
        }
        List<Map.Entry<Tag,Integer>> entryList = new ArrayList<>(editDistanceMap.entrySet());
        entryList.sort(Map.Entry.comparingByValue());
        return entryList.get(0).getKey();
    }

    private Map<User,Double> getUserRecommendScore(Tag tag){
        Set<Question> visitedQuestions = new HashSet<>();
        int weakenTimes = 1;
        Map<User,Double> result = calculateFromTag(tag,weakenTimes,visitedQuestions);
        weakenTimes *= WEAKEN_FACTOR;
        Set<Tag> superiorOrInferiorTags = tagRepository.getSuperiorOrInferiorTagsById(tag.getId());
        for(Tag superiorOrInferiorTag:superiorOrInferiorTags){
            Map<User,Double> subResult = calculateFromTag(superiorOrInferiorTag,weakenTimes,visitedQuestions);
            subResult.forEach((key, value) -> result.merge(key, value, Double::sum));
        }
        return result;
    }

    private Map<User,Double> calculateFromTag(Tag tag,Integer weakenTimes, Set<Question> visitedQuestions){
        Map<User,Double> result = new HashMap<>();
        Set<Question> relatedQuestions = questionRepository.findByTagId(tag.getId());
        Set<Question> similarQuestions = new HashSet<>();
        for(Question question:relatedQuestions){
            similarQuestions.addAll(questionRepository.getSimilarQuestionsById(question.getId()));
            if (visitedQuestions.contains(question))
                continue;
            Map<User,Double> subResult = calculateFromQuestion(question,weakenTimes);
            subResult.forEach((key, value) -> result.merge(key, value, Double::sum));
            visitedQuestions.add(question);
        }
        weakenTimes *= WEAKEN_FACTOR;
        for(Question question:similarQuestions){
            if (visitedQuestions.contains(question))
                continue;
            Map<User,Double> subResult = calculateFromQuestion(question,weakenTimes);
            subResult.forEach((key, value) -> result.merge(key, value, Double::sum));
            visitedQuestions.add(question);
        }
        return result;
    }

    private Map<User,Double> calculateFromQuestion(Question question,Integer weakenTimes){
        Map<User,Double> result = new HashMap<>();
        double weight = (Utils.normalize(questionMinVote,questionMaxVote,question.getVote())
                + Utils.normalize(questionMinViewedTimes,questionMaxViewedTimes,question.getViewedTimes())
                + Utils.normalize(questionMinAnswerNum,questionMaxAnswerNum,question.getAnswerNum())
                + Utils.normalize(questionMinLength,questionMaxLength,question.getLength())
        )/weakenTimes;
        Set<Answer> answers = answerRepository.getByQuestionId(question.getId());
        for (Answer answer:answers){
            User user = userRepository.getByAnswerId(answer.getId());
            result.merge(user,weight*answer.getVote(),Double::sum);
        }
        return result;
    }
}
