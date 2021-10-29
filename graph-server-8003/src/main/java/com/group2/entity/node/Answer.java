package com.group2.entity.node;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@Data
@EqualsAndHashCode(callSuper = true)
@NodeEntity("answer")
public class Answer extends AbstractNode{
    @Property
    private String name;
    @Property
    private Integer vote;
}
