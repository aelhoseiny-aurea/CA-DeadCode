package com.aurea.ca.deadcode.endpoint;

import com.aurea.ca.deadcode.project.AddRepositoryRequest;
import com.aurea.ca.deadcode.project.ProjectDto;
import com.aurea.ca.deadcode.project.ProjectService;
import com.aurea.ca.deadcode.project.deadcode.ProjectDeadCodeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ameen on 07/04/17.
 */
@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/project/repository/add", method = RequestMethod.POST)
    public ProjectDto addRepository(@Validated @RequestBody AddRepositoryRequest addRepositoryRequest) {
        return projectService.addRepository(addRepositoryRequest);

    }

    @RequestMapping(value = "/project/list", method = RequestMethod.GET)
    public List<ProjectDto> listAll() {
        return projectService.getAllProjects();

    }

    @RequestMapping(value = "/project/repository/dead-code/get", method = RequestMethod.GET)
    public ProjectDeadCodeDto getRepositoryDeadCode(@RequestParam(value = "url") String url) {
        return projectService.getProjectDeadCode(url);
    }
}
