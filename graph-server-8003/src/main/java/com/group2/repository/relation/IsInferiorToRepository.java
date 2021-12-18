package com.group2.repository.relation;

import com.group2.entity.relation.AbstractRelation;
import com.group2.entity.relation.IsInferiorTo;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IsInferiorToRepository extends Neo4jRepository<IsInferiorTo,Long>, AbstractRelationRepository{
    @Override
    @Query("MATCH p=(n)-[:is_inferior_to]->() where id(n)=$id return p skip $skip limit $limit")
    List<IsInferiorTo> findByStartNodeId(@Param("id")Long id, @Param("skip")Long skip, @Param("limit")Integer limit);

    @Query("match p=()-[r:is_inferior_to]->() return p order by r.reliability desc skip $skip limit $limit")
    List<IsInferiorTo> sortAndLimit(@Param("skip") Integer skip, @Param("limit") Integer limit);
}
