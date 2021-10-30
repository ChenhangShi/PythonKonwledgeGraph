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
@RelationshipEntity("contains")
public class Contains extends AbstractRelation{
    @StartNode
    private Question question;
    @EndNode
    private Tag tag;

    @Override
    public Question getStartNode(){
        return question;
    }

    @Override
    public Tag getEndNode(){
        return tag;
    }
}
