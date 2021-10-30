package com.group2.repository.relation;

import com.group2.entity.relation.Published;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PublishedRepository extends Neo4jRepository<Published,Long>,AbstractRelationRepository {
}
