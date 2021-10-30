package com.group2.repository.relation;

import com.group2.entity.relation.BelongsTo;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface BelongsToRepository extends Neo4jRepository<BelongsTo,Long>,AbstractRelationRepository {
}
