package com.group2.repository.node;

import com.group2.entity.node.Tag;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface TagRepository extends Neo4jRepository<Tag,Long> {
    Tag findByName(String name);

    @Query("match (n:tag) where n.name contains $s OR $s contains n.name return n")
    Set<Tag> findPossibleTagsByName(@Param("s")String s);

    @Query("match (n:tag)-[:is_superior_to|is_inferior_to]->(m:tag) where id(n) = $id return m")
    Set<Tag> getSuperiorOrInferiorTagsById(@Param("id") Long id);
}
