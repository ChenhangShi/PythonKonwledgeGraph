package com.group2.config;

import com.group2.repository.relation.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class VisualizationConfig {
    @Bean
    // @Bean产生的默认名称就是方法名
//    @Qualifier("for_tag")
    public List<AbstractRelationRepository> tagAbstractRelationRepos(IsRelatedToRepository isRelatedToRepository, IsSuperiorToRepository isSuperiorToRepository, IsInferiorToRepository isInferiorToRepository){
        return new ArrayList<AbstractRelationRepository>(){{add(isRelatedToRepository);add(isSuperiorToRepository);add(isInferiorToRepository);}};
    }

    @Bean("for_question")
//    @Qualifier("for_question")
    public List<AbstractRelationRepository> questionAbstractRelationRepos(ContainsRepository containsRepository, HasAnswerRepository hasAnswerRepository, IsSimilarToRepository isSimilarToRepository){
        return new ArrayList<AbstractRelationRepository>(){{add(containsRepository);add(hasAnswerRepository);add(isSimilarToRepository);}};
    }

    @Bean
    @Qualifier("for_answer")
    public List<AbstractRelationRepository> answerAbstractRelationRepos(IsPublishedByRepository isPublishedByRepository, BelongsToRepository belongsToRepository){
        return new ArrayList<AbstractRelationRepository>(){{add(isPublishedByRepository);add(belongsToRepository);}};
    }

    @Bean
    @Qualifier("for_user")
    public List<AbstractRelationRepository> userAbstractRelationRepos(PublishedRepository publishedRepository){
        return new ArrayList<AbstractRelationRepository>(){{add(publishedRepository);}};
    }

    @Bean
    @Qualifier("each_label")
    public Map<String,List<AbstractRelationRepository>> eachLabelAbstractRelationRepos(
            @Qualifier("tagAbstractRelationRepos")List<AbstractRelationRepository> tagAbstractRelationRepos,
            @Qualifier("for_question")List<AbstractRelationRepository> questionAbstractRelationRepos,
            @Qualifier("for_answer")List<AbstractRelationRepository> answerAbstractRelationRepos,
            @Qualifier("for_user")List<AbstractRelationRepository> userAbstractRelationRepos
    ){
        return new HashMap<String,List<AbstractRelationRepository>>(){{
            put("tag",tagAbstractRelationRepos);
            put("question",questionAbstractRelationRepos);
            put("answer",answerAbstractRelationRepos);
            put("user",userAbstractRelationRepos);
        }};
    }
}
