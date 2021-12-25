package com.group2.repository.relation;

import com.group2.entity.relation.IsSimilarTo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class IsSimilarToRepositoryTest {
    @Autowired
    private IsSimilarToRepository isSimilarToRepository;

    @Test
    public void testSortAndLimit(){
        List<IsSimilarTo> isSimilarToList = isSimilarToRepository.sortAndLimit(0,10);
        for(IsSimilarTo isSimilarTo:isSimilarToList)
            System.out.println(isSimilarTo.getReliability());
    }
}
