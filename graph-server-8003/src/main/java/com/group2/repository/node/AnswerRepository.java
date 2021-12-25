package com.group2.repository.node;

import com.group2.entity.node.Answer;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface AnswerRepository extends Neo4jRepository<Answer,Long> {
    @Query("match (n:question)-[:has_answer]->(m:answer) where id(n) = $questionId return m")
    Set<Answer> getByQuestionId(@Param("questionId") Long questionId);
}
