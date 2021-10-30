package com.group2.service;

import com.group2.repository.relation.AbstractRelationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class GraphServiceTest {
    @Autowired
    private GraphService graphService;
}
