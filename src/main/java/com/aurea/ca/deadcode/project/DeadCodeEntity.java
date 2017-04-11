package com.aurea.ca.deadcode.project;

import com.aurea.ca.deadcode.project.deadcode.EntityType;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by ameen on 10/04/17.
 */
@Entity
@Data
@ToString(exclude = "project")
public class DeadCodeEntity {

    @Id
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "project_id")
    private Project project;

    private String name;

    @Enumerated(EnumType.STRING)
    private EntityType entityType;

    private String entityPackage;
    private String file;
    private Integer line;
    private Integer column;


}
