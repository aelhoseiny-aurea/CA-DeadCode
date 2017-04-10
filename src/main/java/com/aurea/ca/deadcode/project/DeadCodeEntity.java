package com.aurea.ca.deadcode.project;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by ameen on 10/04/17.
 */
@Entity
public class DeadCodeEntity {

    @Id
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "project_id")
    private Project project;


}
