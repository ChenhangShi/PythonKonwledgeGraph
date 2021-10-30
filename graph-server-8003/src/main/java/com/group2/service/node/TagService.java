package com.group2.service.node;

import com.group2.entities.CommonResult;
import com.group2.entity.node.Tag;
import com.group2.repository.node.TagRepository;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public CommonResult<Tag> getTagByName(String name){
        Tag tag = tagRepository.findByName(name);
        if(tag == null)
            return new CommonResult<>(404,"tag not exist");
        tag.setLabel(tag.getClass().getAnnotation(NodeEntity.class).value());
        return new CommonResult<>(200, tag.getLabel() + " " + tag.getName(), tag);
    }
}
