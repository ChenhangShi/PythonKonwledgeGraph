package com.group2.repository;

import com.group2.entity.node.Tag;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends Neo4jRepository<Tag,Long> {
    Tag findByName(String name);
}
