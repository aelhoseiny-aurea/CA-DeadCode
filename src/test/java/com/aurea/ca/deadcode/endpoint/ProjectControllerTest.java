package com.aurea.ca.deadcode.endpoint;

import com.aurea.ca.deadcode.CaDeadcodeApplicationTests;
import com.aurea.ca.deadcode.project.AddRepositoryRequest;
import com.aurea.ca.deadcode.project.ProjectDto;
import com.aurea.ca.deadcode.project.ProjectStatus;
import com.aurea.ca.deadcode.understand.Languages;
import com.aurea.ca.deadcode.utilities.FileUtilities;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


/**
 * Created by ameen on 07/04/17.
 */
@Transactional
public class ProjectControllerTest extends CaDeadcodeApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private FileUtilities fileUtilities;

    @Value("${test.git.test_repo.url}")
    private String testRepoUrl;


    @Test
    public void addRepositorySuccessfully() {
        AddRepositoryRequest addRepositoryRequest = new AddRepositoryRequest();
        addRepositoryRequest.setLanguage(Languages.JAVA);
        addRepositoryRequest.setUrl(testRepoUrl);
        ResponseEntity<ProjectDto> actualProjectDtoResponseEntity = testRestTemplate
            .postForEntity("/project/repository/add", addRepositoryRequest, ProjectDto.class);
        Assert.assertTrue("no reposnse entity returned", actualProjectDtoResponseEntity != null);

        assertThat(actualProjectDtoResponseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(actualProjectDtoResponseEntity.getBody().getStatus(),
            equalTo(ProjectStatus.PROCESSING));
        assertThat(actualProjectDtoResponseEntity.getBody().getName(),
            equalTo(fileUtilities.extractRepositoryName(testRepoUrl)));
    }

    @Test
    public void listRepositorySuccessfully() {
        //Given the project is added
        Integer sizeBeforeTest = testRestTemplate.getForEntity("/project/list", ProjectDto[].class).getBody().length;
        AddRepositoryRequest addRepositoryRequest = new AddRepositoryRequest();
        addRepositoryRequest.setLanguage(Languages.JAVA);
        addRepositoryRequest.setUrl(testRepoUrl);
        ResponseEntity<ProjectDto> actualProjectDtoResponseEntity = testRestTemplate
            .postForEntity("/project/repository/add", addRepositoryRequest, ProjectDto.class);
        Assert.assertTrue("no reposnse entity returned", actualProjectDtoResponseEntity != null);

        assertThat(actualProjectDtoResponseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(actualProjectDtoResponseEntity.getBody().getStatus(),
            equalTo(ProjectStatus.PROCESSING));
        assertThat(actualProjectDtoResponseEntity.getBody().getName(),
            equalTo(fileUtilities.extractRepositoryName(testRepoUrl)));
        //When
        ResponseEntity<ProjectDto[]> projectsListResponse =
            testRestTemplate.getForEntity("/project/list", ProjectDto[].class);
        //Then
        assertThat(projectsListResponse.getBody().length, equalTo(sizeBeforeTest + 1));
        assertThat(((ProjectDto) projectsListResponse.getBody()[0]).getName(),
            equalTo(fileUtilities.extractRepositoryName(testRepoUrl)));

    }
}
