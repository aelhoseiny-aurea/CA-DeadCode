package com.aurea.ca.deadcode.project;

import com.aurea.ca.deadcode.reactive.EventsPublisher;
import com.aurea.ca.deadcode.reactive.events.NewProjectEvent;
import com.aurea.ca.deadcode.utilities.FileUtilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    @Autowired
    private ObjectMapper mapper;


    public ProjectDto addRepository(AddRepositoryRequest addRepositoryRequest) {
        //create new project
        Project project = new Project();
        project.setGitUrl(addRepositoryRequest.getUrl());
        project.setName(fileUtilities.extractRepositoryName(addRepositoryRequest.getUrl()));
        project.setLanguage(addRepositoryRequest.getLanguage());
        project.setStatus(ProjectStatus.PROCESSING);
        project.setSubmitDate(Calendar.getInstance().getTime());
        project = save(project);
        //publish NEW REPOISTORY EVENT
        eventsPublisher.publish(new NewProjectEvent(project));

        return mapper.convertValue(project, ProjectDto.class);
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public List<ProjectDto> getAllProjects() {
        Iterable<Project> allProjects = Lists.newArrayList(projectRepository.findAll());
        List<ProjectDto> result = new ArrayList<>();
        for (Project project : allProjects) {
            ProjectDto projectDto = mapper.convertValue(project, ProjectDto.class);
            result.add(projectDto);
        }
        return result;
    }
}
