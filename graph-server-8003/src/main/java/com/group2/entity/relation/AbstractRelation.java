package com.group2.entity.relation;

import com.group2.entity.AbstractEntity;
import com.group2.entity.node.AbstractNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.RelationshipEntity;

@Data
@EqualsAndHashCode(callSuper = true,exclude = "type")
public abstract class AbstractRelation extends AbstractEntity {
    private String type;

    public abstract AbstractNode getStartNode();
    public abstract AbstractNode getEndNode();

    public void setType(){
        RelationshipEntity relationshipEntity = getClass().getAnnotation(RelationshipEntity.class);
        type = !relationshipEntity.value().equals("") ? relationshipEntity.value() : relationshipEntity.type();
    }
}
