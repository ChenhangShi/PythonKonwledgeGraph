package com.group2.entity.relation;

import com.group2.entity.node.Question;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@Data
@EqualsAndHashCode(callSuper = true)
@RelationshipEntity("is_similar_to")
public class IsSimilarTo extends AbstractRelation{
    @StartNode
    private Question question1;
    @EndNode
    private Question question2;

    @Override
    public Question getStartNode(){
        return question1;
    }

    @Override
    public Question getEndNode(){
        return question2;
    }
}
