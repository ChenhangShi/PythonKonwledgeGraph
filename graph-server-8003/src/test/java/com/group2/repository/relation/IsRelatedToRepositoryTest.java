package com.group2.repository.relation;

import com.group2.entity.relation.AbstractRelation;
import com.group2.entity.relation.IsRelatedTo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class IsRelatedToRepositoryTest {
    @Autowired
    IsRelatedToRepository isRelatedToRepository;

    @Test
    public void testFindByStartNodeId(){
        List<IsRelatedTo> isRelatedToList = isRelatedToRepository.findByStartNodeId(26348L,0L,10);
        for (AbstractRelation isRelatedTo: isRelatedToList)
            isRelatedTo.setType();
        System.out.println(isRelatedToList.get(0).getType());
    }
}
