package com.group2.entity.node;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@Data
@EqualsAndHashCode(callSuper = true)
@NodeEntity("question")
public class Question extends AbstractNode{
    @Property
    private String title;
    @Property
    private Integer length;
    @Property
    private Integer vote;
    @Property("viewed_times")
    private Integer viewedTimes;
    @Property("answer_num")
    private Integer answerNum;
}
