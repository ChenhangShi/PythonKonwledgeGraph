package com.group2.dto;

import com.group2.entity.node.AbstractNode;
import lombok.Data;

import java.util.Set;

@Data
public class Relationships {
    private Set<AbstractNode> relatedNodes;
    private Set<TripleIdRelation> relations;
}
