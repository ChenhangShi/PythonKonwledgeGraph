package com.group2.repository.relation;

import com.group2.entity.relation.IsSimilarTo;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface IsSimilarToRepository extends Neo4jRepository<IsSimilarTo,Long>, AbstractRelationRepository{
}
