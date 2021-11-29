package com.group2.repository.node;

import com.group2.entity.node.Question;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface QuestionRepository extends Neo4jRepository<Question,Long> {
    @Query("match (n:tag)-[:is_related_to]->(m:question) where id(n) = $tagId return m")
    Set<Question> findByTagId(@Param("tagId") Long tagId);

    @Query("match (n:question)-[:is_similar_to]->(m:question) where id(n) = $id return m")
    Set<Question> getSimilarQuestionsById(@Param("id") Long id);
}
