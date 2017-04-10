package com.aurea.ca.deadcode.project;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ameen on 10/04/17.
 */
@Repository
public interface DeadCodeEntityRepository extends CrudRepository<DeadCodeEntity, Integer> {
    List<DeadCodeEntity> findByProject(Project project);
}
