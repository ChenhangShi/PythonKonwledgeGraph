package com.group2.repository.node;

import com.group2.entity.node.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testGetByAnswerId(){
        User user = userRepository.getByAnswerId(102977L);
        System.out.println(user.getUrl());
    }
}
