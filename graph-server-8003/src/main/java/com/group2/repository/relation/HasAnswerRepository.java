package com.group2.repository.relation;

import com.group2.entity.relation.HasAnswer;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface HasAnswerRepository extends Neo4jRepository<HasAnswer,Long>,AbstractRelationRepository {
}
