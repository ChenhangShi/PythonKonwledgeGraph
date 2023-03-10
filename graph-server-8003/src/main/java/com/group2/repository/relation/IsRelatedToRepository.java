package com.group2.repository.relation;

import com.group2.entity.relation.AbstractRelation;
import com.group2.entity.relation.IsRelatedTo;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IsRelatedToRepository extends Neo4jRepository<IsRelatedTo,Long>, AbstractRelationRepository{
    @Override
    @Query("MATCH p=(n)-[:is_related_to]->() where id(n)=$id return p skip $skip limit $limit")
    List<IsRelatedTo> findByStartNodeId(@Param("id")Long id, @Param("skip")Long skip, @Param("limit")Integer limit);
}
