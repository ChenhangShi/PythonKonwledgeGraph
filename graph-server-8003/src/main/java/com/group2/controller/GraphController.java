package com.group2.controller;

import com.group2.dto.Relationships;
import com.group2.entities.CommonResult;
import com.group2.entity.node.AbstractNode;
import com.group2.service.GraphService;
import com.group2.service.node.TagService;
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
    @Autowired
    private GraphService graphService;

    @GetMapping("/get_first_node")
    public CommonResult<? extends AbstractNode> getFirstNode(){
        return tagService.getTagByName("python");
    }

    @GetMapping("/get_my_relationships")
    public CommonResult<Relationships> getMyRelationships(@RequestParam Long id, @RequestParam String label, @RequestParam(defaultValue = "0") Integer page){
        return graphService.getMyRelationships(id,label,page);
    }
}
