package com.group2.repository.relation;

import com.group2.entity.relation.AbstractRelation;
import com.group2.entity.relation.HasAnswer;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HasAnswerRepository extends Neo4jRepository<HasAnswer,Long>,AbstractRelationRepository {
    @Override
    @Query("MATCH p=(n)-[:has_answer]->() where id(n)=$id return p skip $skip limit $limit")
    List<AbstractRelation> findByStartNodeId(@Param("id")Long id, @Param("skip")Long skip, @Param("limit")Integer limit);
}
