package com.aurea.ca.deadcode.project;

import lombok.Data;

import java.util.Date;

/**
 * Created by ameen on 07/04/17.
 */
@Data
public class ProjectDto {
    private Integer id;
    private String name;
    private ProjectStatus status;
    private Date submitDate;
    private Date completeProcessingDate;

}