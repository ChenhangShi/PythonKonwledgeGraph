package com.group2.repository.node;

import com.group2.entity.node.Answer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@SpringBootTest
@Transactional
public class AnswerRepositoryTest {
    @Autowired
    private AnswerRepository answerRepository;

    @Test
    public void testGetByQuestionId(){
        Set<Answer> answers = answerRepository.getByQuestionId(0L);
        for (Answer answer:answers)
            System.out.println(answer.getName());
    }
}
