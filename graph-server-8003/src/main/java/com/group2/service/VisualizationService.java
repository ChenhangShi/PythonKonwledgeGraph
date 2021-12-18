package com.group2.service;

import com.group2.dto.Relationships;
import com.group2.dto.TripleIdRelation;
import com.group2.dto.TripleIdRelationWithReliability;
import com.group2.entities.CommonResult;
import com.group2.entity.node.AbstractNode;
import com.group2.entity.node.Question;
import com.group2.entity.relation.AbstractRelation;
import com.group2.entity.relation.IsInferiorTo;
import com.group2.entity.relation.IsSimilarTo;
import com.group2.entity.relation.IsSuperiorTo;
import com.group2.repository.relation.AbstractRelationRepository;
import com.group2.repository.relation.IsInferiorToRepository;
import com.group2.repository.relation.IsSimilarToRepository;
import com.group2.repository.relation.IsSuperiorToRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class VisualizationService {
    private static final int PAGE_SIZE = 5;

    @Autowired
    @Qualifier("each_label") // 不写qualifier，会用4个list的bean组合成map注入，而不是规定的map
    private Map<String, List<AbstractRelationRepository>> eachLabelAbstractRelationRepos;
    @Autowired
    private IsSimilarToRepository isSimilarToRepository;
    @Autowired
    private IsSuperiorToRepository isSuperiorToRepository;
    @Autowired
    private IsInferiorToRepository isInferiorToRepository;

    public CommonResult<Relationships> getMyRelationships(Long id,String label,Integer page){
        List<AbstractRelationRepository> relationRepositories = eachLabelAbstractRelationRepos.get(label);
        List<AbstractRelation> relations = new ArrayList<>();
        for(AbstractRelationRepository abstractRelationRepository:relationRepositories){
            List<? extends AbstractRelation> curRelations = abstractRelationRepository.findByStartNodeId(id, ((long) page *PAGE_SIZE),PAGE_SIZE);
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

    public CommonResult<Relationships> getIsSimilarToRelationships(Integer from, Integer to){
        if (from<1 || from>to)
            return new CommonResult<>(500,"区间不正确");
        Integer skip = from-1;
        Integer limit = (to-from+1)*2;
        List<IsSimilarTo> isSimilarToList = isSimilarToRepository.sortAndLimit(skip,limit);
        Set<AbstractNode> questionSet = new HashSet<>();
        Set<TripleIdRelation> tripleIdRelationWithReliabilitySet = new HashSet<>();
        for(IsSimilarTo isSimilarTo:isSimilarToList){
            isSimilarTo.getStartNode().setLabel();
            isSimilarTo.getEndNode().setLabel();
            questionSet.add(isSimilarTo.getStartNode());
            questionSet.add(isSimilarTo.getEndNode());
            TripleIdRelationWithReliability tripleIdRelationWithReliability = new TripleIdRelationWithReliability();
            tripleIdRelationWithReliability.setId(isSimilarTo.getId());
            tripleIdRelationWithReliability.setFromId(isSimilarTo.getStartNode().getId());
            tripleIdRelationWithReliability.setToId(isSimilarTo.getEndNode().getId());
            tripleIdRelationWithReliability.setType("is_similar_to");
            tripleIdRelationWithReliability.setReliability(isSimilarTo.getReliability());
            tripleIdRelationWithReliabilitySet.add(tripleIdRelationWithReliability);
        }
        Relationships relationships = new Relationships();
        relationships.setRelatedNodes(questionSet);
        relationships.setRelations(tripleIdRelationWithReliabilitySet);
        return new CommonResult<>(200,"success",relationships);
    }

    public CommonResult<Relationships> getIsSuperiorOrInferiorToRelationships(Integer from, Integer to){
        if (from<1 || from>to)
            return new CommonResult<>(500,"区间不正确");
        Integer skip = from-1;
        Integer limit = to-from+1;
        List<IsSuperiorTo> isSuperiorToList = isSuperiorToRepository.sortAndLimit(skip,limit);
        List<IsInferiorTo> isInferiorToList = isInferiorToRepository.sortAndLimit(skip,limit);
        Set<AbstractNode> tagSet = new HashSet<>();
        Set<TripleIdRelation> tripleIdRelationWithReliabilitySet = new HashSet<>();
        for(IsSuperiorTo isSuperiorTo:isSuperiorToList){
            isSuperiorTo.setType();
            isSuperiorTo.getStartNode().setLabel();
            isSuperiorTo.getEndNode().setLabel();
            tagSet.add(isSuperiorTo.getStartNode());
            tagSet.add(isSuperiorTo.getEndNode());
            TripleIdRelationWithReliability tripleIdRelationWithReliability = new TripleIdRelationWithReliability();
            tripleIdRelationWithReliability.setId(isSuperiorTo.getId());
            tripleIdRelationWithReliability.setFromId(isSuperiorTo.getStartNode().getId());
            tripleIdRelationWithReliability.setToId(isSuperiorTo.getEndNode().getId());
            tripleIdRelationWithReliability.setType(isSuperiorTo.getType());
            tripleIdRelationWithReliability.setReliability(isSuperiorTo.getReliability());
            tripleIdRelationWithReliabilitySet.add(tripleIdRelationWithReliability);
        }
        for(IsInferiorTo isInferiorTo:isInferiorToList){
            isInferiorTo.setType();
            isInferiorTo.getStartNode().setLabel();
            isInferiorTo.getEndNode().setLabel();
            tagSet.add(isInferiorTo.getStartNode());
            tagSet.add(isInferiorTo.getEndNode());
            TripleIdRelationWithReliability tripleIdRelationWithReliability = new TripleIdRelationWithReliability();
            tripleIdRelationWithReliability.setId(isInferiorTo.getId());
            tripleIdRelationWithReliability.setFromId(isInferiorTo.getStartNode().getId());
            tripleIdRelationWithReliability.setToId(isInferiorTo.getEndNode().getId());
            tripleIdRelationWithReliability.setType(isInferiorTo.getType());
            tripleIdRelationWithReliability.setReliability(isInferiorTo.getReliability());
            tripleIdRelationWithReliabilitySet.add(tripleIdRelationWithReliability);
        }
        Relationships relationships = new Relationships();
        relationships.setRelatedNodes(tagSet);
        relationships.setRelations(tripleIdRelationWithReliabilitySet);
        return new CommonResult<>(200,"success",relationships);
    }
}
