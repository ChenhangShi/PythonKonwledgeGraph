package com.group2.repository.relation;

import com.group2.entity.relation.IsPublishedBy;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface IsPublishedByRepository extends Neo4jRepository<IsPublishedBy,Long>,AbstractRelationRepository {
}
