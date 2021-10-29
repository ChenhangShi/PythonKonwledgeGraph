package com.group2.service;

import com.group2.entity.node.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class TagSeviceTest {
    @Autowired
    private TagService tagService;

    @Test
    public void testGetTagByName(){
        Tag tag = tagService.getTagByName("python").getData();
        System.out.println(tag.getLabel());
    }
}
