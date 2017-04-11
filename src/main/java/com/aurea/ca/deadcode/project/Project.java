package com.aurea.ca.deadcode.project;

import com.aurea.ca.deadcode.understand.Languages;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ameen on 07/04/17.
 */
@Entity
@Table(
    name = "project",
    uniqueConstraints =
    @UniqueConstraint(columnNames = {"git_url"})
)
@Data
public class Project {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;


    @Column(name = "git_url")
    private String gitUrl;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @Enumerated(EnumType.STRING)
    private Languages language;


    @Column(name = "submit_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date submitDate;

    @Column(name = "complete_processing_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date completeProcessingDate;

    private String error;


}
