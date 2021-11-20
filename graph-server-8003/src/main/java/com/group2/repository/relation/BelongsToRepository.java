package com.group2.repository.relation;

import com.group2.entity.relation.AbstractRelation;
import com.group2.entity.relation.BelongsTo;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BelongsToRepository extends Neo4jRepository<BelongsTo,Long>,AbstractRelationRepository {
    @Override
    @Query("MATCH p=(n)-[:belongs_to]->() where id(n)=$id return p skip $skip limit $limit")
    List<BelongsTo> findByStartNodeId(@Param("id")Long id, @Param("skip")Long skip, @Param("limit")Integer limit);
}
