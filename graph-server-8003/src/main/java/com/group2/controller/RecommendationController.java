package com.group2.controller;

import com.group2.entities.CommonResult;
import com.group2.entity.node.User;
import com.group2.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/graph/recommend")
public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/get_recommended_users")
    public CommonResult<List<User>> getRecommendedUsers(@RequestParam String input) {
        return recommendationService.getRecommendedUsers(preHandleInput(input));
    }

    private String preHandleInput(String input) {
        if (input != null) {
            input = input.trim().toLowerCase();
            input = input.replaceAll("\\s+", "-");
        }
        return input;
    }
}
