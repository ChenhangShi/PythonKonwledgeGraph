package com.group2.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@Data
public abstract class AbstractNodeEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String label;
}
