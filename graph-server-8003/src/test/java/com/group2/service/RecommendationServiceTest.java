package com.group2.service;

import com.group2.entity.node.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SpringBootTest
@Transactional
public class RecommendationServiceTest {
    @Autowired
    private RecommendationService recommendationService;
}
