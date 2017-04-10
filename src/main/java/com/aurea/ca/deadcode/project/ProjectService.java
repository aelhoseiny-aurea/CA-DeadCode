package com.aurea.ca.deadcode.project;

import com.aurea.ca.deadcode.project.deadcode.DeadCodeEntityDto;
import com.aurea.ca.deadcode.project.deadcode.ProjectDeadCodeDto;
import com.aurea.ca.deadcode.reactive.EventsPublisher;
import com.aurea.ca.deadcode.reactive.events.NewProjectEvent;
import com.aurea.ca.deadcode.utilities.FileUtilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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

    @Autowired
    private DeadCodeEntityRepository deadCodeEntityRepository;


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
        List<Project> allProjects = Lists.newArrayList(projectRepository.findAll());
        return convertValues(allProjects, ProjectDto.class);
    }

    public ProjectDeadCodeDto getProjectDeadCode(String url) {
        Project project = projectRepository.findByGitUrl(url);
        Assert.notNull(project, "This Git URL need to be added.");
        if (project.getStatus().equals(ProjectStatus.FAILED)) {
            throw new IllegalArgumentException("The project {" + project.getName() + "} couldn't be analyzed");
        }
        Assert.isTrue(project.getStatus().equals(ProjectStatus.COMPLETED), "This Git URL need to be added.");
        ProjectDeadCodeDto projectDeadCodeDto = new ProjectDeadCodeDto();
        projectDeadCodeDto.setDeadCodeEntities(convertValues(deadCodeEntityRepository.findByProject(project),
            DeadCodeEntityDto.class));
        return null;
    }


    public <D, S> List<D> convertValues(List<S> sourceList, Class<D> d) {
        List<D> result = new ArrayList<>();
        for (S source : sourceList) {
            D destination = mapper.convertValue(source, d);
            result.add(destination);
        }
        return result;
    }
}
