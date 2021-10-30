package com.group2.entity.node;

import com.group2.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.NodeEntity;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractNode extends AbstractEntity {
    private String label;

    public void setLabel(){
        NodeEntity nodeEntity = getClass().getAnnotation(NodeEntity.class);
        label = !nodeEntity.value().equals("") ? nodeEntity.value() : nodeEntity.label();
    }
}
