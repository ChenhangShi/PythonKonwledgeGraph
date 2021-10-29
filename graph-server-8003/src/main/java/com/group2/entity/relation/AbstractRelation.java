package com.group2.entity.relation;

import com.group2.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractRelation extends AbstractEntity {
    private String type;
}
