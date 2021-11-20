package com.group2.repository.relation;

import com.group2.entity.relation.AbstractRelation;
import com.group2.entity.relation.IsSuperiorTo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class IsSuperiorToRepositoryTest {
    @Autowired
    IsSuperiorToRepository isSuperiorToRepository;

    @Test
    public void testFindByStartNodeId(){
        List<IsSuperiorTo> isSuperiorToList = isSuperiorToRepository.findByStartNodeId(27384L,0L,10);
        for (AbstractRelation isSuperiorTo: isSuperiorToList)
            isSuperiorTo.setType();
        System.out.println(isSuperiorToList.get(0).getType());
    }
}
