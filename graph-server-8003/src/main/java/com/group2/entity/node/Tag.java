package com.group2.entity.node;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@Data
@EqualsAndHashCode(callSuper = true)
@NodeEntity("tag")
public class Tag extends AbstractNode {
    @Property
    private String name;
}
