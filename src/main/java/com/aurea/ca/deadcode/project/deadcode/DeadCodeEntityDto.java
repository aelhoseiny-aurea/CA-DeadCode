package com.aurea.ca.deadcode.project.deadcode;

import lombok.Data;

/**
 * Created by ameen on 10/04/17.
 */
@Data
public class DeadCodeEntityDto {
    private EntityType entityType;
    private Integer column;
    private Integer line;
    private String entityPackage;
    private String file;

}
