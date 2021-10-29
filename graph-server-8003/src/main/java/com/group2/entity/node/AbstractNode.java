package com.group2.entity.node;

import com.group2.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractNode extends AbstractEntity {
    private String label;
}
