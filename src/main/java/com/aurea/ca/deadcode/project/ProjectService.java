package com.aurea.ca.deadcode.project;

import com.aurea.ca.deadcode.reactive.EventsPublisher;
import com.aurea.ca.deadcode.reactive.events.NewProjectEvent;
import com.aurea.ca.deadcode.utilities.FileUtilities;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ameen on 07/04/17.
 */
@Service
public class ProjectService {

    @Autowired
    private FileUtilities fileUtilities;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EventsPublisher eventsPublisher;


    public ProjectDto addRepository(AddRepositoryRequest addRepositoryRequest) {
        //create new project
        Project project = new Project();
        project.setGitUrl(addRepositoryRequest.getUrl());
        project.setName(fileUtilities.extractRepositoryName(addRepositoryRequest.getUrl()));
        project.setLanguage(addRepositoryRequest.getLanguage());
        project.setStatus(ProjectStatus.PROCESSING);
        project = save(project);
        //publish NEW REPOISTORY EVENT
        eventsPublisher.publish(new NewProjectEvent(project));

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.convertValue(project, ProjectDto.class);
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }
}
