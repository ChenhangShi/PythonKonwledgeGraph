package com.group2.entity.relation;

import com.group2.entity.node.Answer;
import com.group2.entity.node.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@Data
@EqualsAndHashCode(callSuper = true)
@RelationshipEntity("published")
public class Published extends AbstractRelation{
    @StartNode
    private User user;
    @EndNode
    private Answer answer;
}
