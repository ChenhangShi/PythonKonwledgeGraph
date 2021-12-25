package com.group2.repository.relation;

import com.group2.entity.relation.AbstractRelation;

import java.util.List;

public interface AbstractRelationRepository {
    List<? extends AbstractRelation> findByStartNodeId(Long id, Long skip, Integer limit);
}
