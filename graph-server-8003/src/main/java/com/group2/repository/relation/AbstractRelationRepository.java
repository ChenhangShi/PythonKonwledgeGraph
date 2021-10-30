package com.group2.repository.relation;

import com.group2.entity.relation.AbstractRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AbstractRelationRepository {
    @Query("MATCH p=(n)-[]->() where id(n)=$id return p skip $skip limit $limit")
    List<AbstractRelation> findByStartNodeId(@Param("id")Long id, @Param("skip")Long skip, @Param("limit")Integer limit);
}
