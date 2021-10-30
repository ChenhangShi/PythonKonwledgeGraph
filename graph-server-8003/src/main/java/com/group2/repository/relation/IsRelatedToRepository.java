package com.group2.repository.relation;

import com.group2.entity.relation.IsRelatedTo;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface IsRelatedToRepository extends Neo4jRepository<IsRelatedTo,Long>, AbstractRelationRepository{
}
