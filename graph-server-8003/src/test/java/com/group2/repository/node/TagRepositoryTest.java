package com.group2.repository.node;

import com.group2.entity.node.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@SpringBootTest
@Transactional
public class TagRepositoryTest {
    @Autowired
    TagRepository tagRepository;

    @Test
    public void testGetPython(){
        Tag tag = tagRepository.findByName("python");
        System.out.println(tag.getName());
    }

    @Test
    public void testFindPossibleTagsByName(){
        Set<Tag> tags = tagRepository.findPossibleTagsByName("list");
        for (Tag tag: tags) {
            System.out.println(tag.getName());
        }
    }

    @Test
    public void testGetSuperiorOrInferiorTagsById(){
        Set<Tag> tags = tagRepository.getSuperiorOrInferiorTagsById(27384L);
        for(Tag tag:tags)
            System.out.println(tag.getName());
    }
}
