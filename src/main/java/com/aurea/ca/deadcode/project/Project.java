package com.aurea.ca.deadcode.project;

import com.aurea.ca.deadcode.understand.Languages;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by ameen on 07/04/17.
 */
@Entity
@Data
public class Project {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String gitUrl;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @Enumerated(EnumType.STRING)
    private Languages language;
}
