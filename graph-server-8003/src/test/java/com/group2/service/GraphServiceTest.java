package com.group2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class GraphServiceTest {
    @Autowired
    private VisualizationService visualizationService;
}
