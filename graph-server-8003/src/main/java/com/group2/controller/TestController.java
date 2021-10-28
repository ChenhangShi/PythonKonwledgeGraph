package com.group2.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    @GetMapping("/get")
    public List<Object> get(){
        Son1 a = new Son1();
        a.setId(0);
        a.setA("a");
        Son2 b = new Son2();
        b.setId(1);
        b.setB("b");
        List<Object> l = new ArrayList<>();
        l.add(a);
        l.add(b);
        return l;
    }
}

@Data
class Parent{
    private int id;
}

@Data
class Son1 extends Parent{
    private String a;
}

@Data
class Son2 extends Parent{
    private String b;
}