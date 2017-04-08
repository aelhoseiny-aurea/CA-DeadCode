package com.aurea.ca.deadcode.reactive.Consumers;

import com.aurea.ca.deadcode.git.GitService;
import com.aurea.ca.deadcode.project.Project;
import com.aurea.ca.deadcode.project.ProjectService;
import com.aurea.ca.deadcode.project.ProjectStatus;
import com.aurea.ca.deadcode.reactive.DeadCodeChannels;
import com.aurea.ca.deadcode.reactive.events.NewProjectEvent;
import com.aurea.ca.deadcode.understand.UnderstandService;
import com.aurea.ca.deadcode.understand.exceptions.CouldNotExecuteUnderstandCommand;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.bus.Event;

import java.io.File;
import java.io.IOException;

/**
 * Created by ameen on 08/04/17.
 */
@Service
@Slf4j
public class NewProjectConsumer implements DeadCodeConsumer<Event<NewProjectEvent>> {

    @Autowired
    private GitService gitService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UnderstandService understandService;

    @Override
    public void accept(Event<NewProjectEvent> newProjectEventEvent) {
        Project project = newProjectEventEvent.getData().getProject();
        log.debug("new project event received project name:{}, project url:{}",
            project.getName(), project.getGitUrl());
        try {
            File repository = gitService.clone(project.getGitUrl());
            understandService.populateDeadCode(project);

        } catch (GitAPIException | InterruptedException | CouldNotExecuteUnderstandCommand | IOException e) {
            log.error("failed to process new project", e);
            project.setStatus(ProjectStatus.FAILED);
        } finally {
            projectService.save(project);
        }

    }

    @Override
    public DeadCodeChannels getChannel() {
        return DeadCodeChannels.NEW_PROJECT;
    }
}
