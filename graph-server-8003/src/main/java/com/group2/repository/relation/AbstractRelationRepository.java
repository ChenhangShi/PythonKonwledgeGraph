package com.group2.repository.relation;

import com.group2.entity.relation.AbstractRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AbstractRelationRepository {
    List<AbstractRelation> findByStartNodeId(Long id, Long skip, Integer limit);
}
