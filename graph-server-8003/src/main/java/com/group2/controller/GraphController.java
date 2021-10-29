package com.group2.controller;

import com.group2.entities.CommonResult;
import com.group2.entity.AbstractNodeEntity;
import com.group2.entity.TagEntity;
import com.group2.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/graph")
public class GraphController {
    @Autowired
    private TagService tagService;

    @GetMapping("/get_first_node")
    public CommonResult<TagEntity> getFirstNode(){
        return tagService.getTagByName("python");
    }

    @GetMapping("/get_relationships")
    public CommonResult<List<AbstractNodeEntity>> getRelationships(@RequestParam Long id){
        return null;
    }
}
