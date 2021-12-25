package com.group2.repository.node;

import com.group2.entity.node.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends Neo4jRepository<User,Long> {
    @Query("match (n:answer)-[:is_published_by]->(m:user) where id(n)=$answerId return m")
    User getByAnswerId(@Param("answerId") Long answerId);
}
