package com.group2.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@Data
@EqualsAndHashCode(callSuper = true)
@NodeEntity(label = "tag")
public class TagEntity extends AbstractNodeEntity{
    @Property
    private String name;
}
