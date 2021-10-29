package com.group2.entity.relation;

import com.group2.entity.node.Answer;
import com.group2.entity.node.Question;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@Data
@EqualsAndHashCode(callSuper = true)
@RelationshipEntity("highest_voted_answer")
public class HighestVotedAnswer extends AbstractRelation{
    @StartNode
    private Question question;
    @EndNode
    private Answer answer;
}
