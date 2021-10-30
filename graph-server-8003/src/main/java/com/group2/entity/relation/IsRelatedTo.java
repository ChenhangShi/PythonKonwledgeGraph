package com.group2.entity.relation;

import com.group2.entity.node.Question;
import com.group2.entity.node.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@Data
@EqualsAndHashCode(callSuper = true)
@RelationshipEntity("is_related_to")
public class IsRelatedTo extends AbstractRelation{
    @StartNode
    private Tag tag;
    @EndNode
    private Question question;

    @Override
    public Tag getStartNode(){
        return tag;
    }

    @Override
    public Question getEndNode(){
        return question;
    }
}
