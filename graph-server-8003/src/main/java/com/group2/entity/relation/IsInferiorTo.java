package com.group2.entity.relation;

import com.group2.entity.node.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@Data
@EqualsAndHashCode(callSuper = true)
@RelationshipEntity("is_inferior_to")
public class IsInferiorTo extends AbstractRelation{
    @StartNode
    private Tag tag1;
    @EndNode
    private Tag tag2;

    @Override
    public Tag getStartNode(){
        return tag1;
    }

    @Override
    public Tag getEndNode(){
        return tag2;
    }
}
