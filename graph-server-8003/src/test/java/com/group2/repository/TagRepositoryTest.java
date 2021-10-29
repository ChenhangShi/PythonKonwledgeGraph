package com.group2.repository;

import com.group2.entity.TagEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class TagRepositoryTest {
    @Autowired
    TagRepository tagRepository;

    @Test
    public void testGetPython(){
        TagEntity tag = tagRepository.findByName("python");
        System.out.println(tag.getName());
    }
}
