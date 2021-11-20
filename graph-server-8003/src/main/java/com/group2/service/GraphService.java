package com.group2.service;

import com.group2.dto.Relationships;
import com.group2.dto.TripleIdRelation;
import com.group2.entities.CommonResult;
import com.group2.entity.node.AbstractNode;
import com.group2.entity.relation.AbstractRelation;
import com.group2.repository.relation.AbstractRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GraphService {
    private static final int PAGE_SIZE = 10;

    @Autowired
    @Qualifier("each_label") // 不写qualifier，会用4个list的bean组合成map注入，而不是规定的map
    private Map<String, List<AbstractRelationRepository>> eachLabelAbstractRelationRepos;

    public CommonResult<Relationships> getMyRelationships(Long id,String label,Integer page){
        List<AbstractRelationRepository> relationRepositories = eachLabelAbstractRelationRepos.get(label);
        List<AbstractRelation> relations = new ArrayList<>();
        for(AbstractRelationRepository abstractRelationRepository:relationRepositories){
            List<? extends AbstractRelation> curRelations = abstractRelationRepository.findByStartNodeId(id, (long) (page*PAGE_SIZE),PAGE_SIZE);
            relations.addAll(curRelations);
        }
        Set<AbstractNode> relatedNodeSet = new HashSet<>();
        Set<TripleIdRelation> tripleIdRelationSet = new HashSet<>();
        for(AbstractRelation abstractRelation:relations){
            abstractRelation.setType();
            abstractRelation.getEndNode().setLabel();
            relatedNodeSet.add(abstractRelation.getEndNode());
            tripleIdRelationSet.add(
                    new TripleIdRelation(
                            abstractRelation.getId(),
                            abstractRelation.getStartNode().getId(),
                            abstractRelation.getEndNode().getId(),
                            abstractRelation.getType())
            );
        }
        Relationships relationships = new Relationships();
        relationships.setRelatedNodes(relatedNodeSet);
        relationships.setRelations(tripleIdRelationSet);
        return new CommonResult<>(200,"success",relationships);
    }
}
