package com.group2.repository.relation;

import com.group2.entity.relation.Contains;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ContainsRepository extends Neo4jRepository<Contains,Long>,AbstractRelationRepository {
}
