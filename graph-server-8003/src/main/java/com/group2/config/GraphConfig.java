package com.group2.config;

import com.group2.repository.relation.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class GraphConfig {
    @Bean
    @Qualifier("for_tag")
    public List<AbstractRelationRepository> tagAbstractRelationRepos(IsRelatedToRepository isRelatedToRepository){
        return new ArrayList<AbstractRelationRepository>(){{add(isRelatedToRepository);}};
    }

    @Bean
    @Qualifier("for_question")
    public List<AbstractRelationRepository> questionAbstractRelationRepos(ContainsRepository containsRepository, HighestVotedAnswerRepository highestVotedAnswerRepository){
        return new ArrayList<AbstractRelationRepository>(){{add(containsRepository);add(highestVotedAnswerRepository);}};
    }

    @Bean
    @Qualifier("for_answer")
    public List<AbstractRelationRepository> answerAbstractRelationRepos(IsPublishedByRepository isPublishedByRepository, BelongsToRepository belongsToRepository){
        return new ArrayList<AbstractRelationRepository>(){{add(isPublishedByRepository);add(belongsToRepository);}};
    }

    @Bean
    @Qualifier("for_user")
    public List<AbstractRelationRepository> userAbstractRelationRepos(PublishedRepository publishedRepository){
        return new ArrayList<AbstractRelationRepository>(){{add(publishedRepository);}};
    }

    @Bean
    @Qualifier("each_label")
    public Map<String,List<AbstractRelationRepository>> eachLabelAbstractRelationRepos(
            @Qualifier("for_tag")List<AbstractRelationRepository> tagAbstractRelationRepos,
            @Qualifier("for_question")List<AbstractRelationRepository> questionAbstractRelationRepos,
            @Qualifier("for_answer")List<AbstractRelationRepository> answerAbstractRelationRepos,
            @Qualifier("for_user")List<AbstractRelationRepository> userAbstractRelationRepos
    ){
        return new HashMap<String,List<AbstractRelationRepository>>(){{
            put("tag",tagAbstractRelationRepos);
            put("question",questionAbstractRelationRepos);
            put("answer",answerAbstractRelationRepos);
            put("user",userAbstractRelationRepos);
        }};
    }
}
