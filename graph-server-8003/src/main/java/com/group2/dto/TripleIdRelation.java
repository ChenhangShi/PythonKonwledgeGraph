package com.group2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripleIdRelation {
    private Long id;
    private Long fromId;
    private Long toId;
    private String type;
}
