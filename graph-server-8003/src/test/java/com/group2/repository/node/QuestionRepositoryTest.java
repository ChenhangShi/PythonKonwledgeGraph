package com.group2.repository.node;

import com.group2.entity.node.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@SpringBootTest
@Transactional
public class QuestionRepositoryTest {
    @Autowired
    private QuestionRepository questionRepository;

    @Test
    public void testFindByTagId(){
        Set<Question> questions = questionRepository.findByTagId(26193L);
        for(Question question:questions)
            System.out.println(question.getTitle());
    }

    @Test
    public void testGetSimilarQuestionsById(){
        Set<Question> questions = questionRepository.getSimilarQuestionsById(9010L);
        for(Question question:questions)
            System.out.println(question.getTitle());
    }
}
