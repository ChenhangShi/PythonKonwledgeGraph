package com.group2.repository.relation;

import com.group2.entity.relation.HighestVotedAnswer;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface HighestVotedAnswerRepository extends Neo4jRepository<HighestVotedAnswer,Long>,AbstractRelationRepository {
}
