package com.aurea.ca.deadcode.project;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by ameen on 07/04/17.
 */
public interface ProjectRepository extends CrudRepository<Project, Integer> {
    Project findByGitUrl(String url);
}
