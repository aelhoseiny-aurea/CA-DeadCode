package com.aurea.ca.deadcode.endpoint;

import com.aurea.ca.deadcode.CaDeadcodeApplicationTests;
import com.aurea.ca.deadcode.project.AddRepositoryRequest;
import com.aurea.ca.deadcode.project.ProjectDto;
import com.aurea.ca.deadcode.project.ProjectService;
import com.aurea.ca.deadcode.project.ProjectStatus;
import com.aurea.ca.deadcode.project.deadcode.ProjectDeadCodeDto;
import com.aurea.ca.deadcode.understand.Languages;
import com.aurea.ca.deadcode.utilities.FileUtilities;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by ameen on 07/04/17.
 */
@Transactional
public class ProjectControllerTest extends CaDeadcodeApplicationTests {

    @Autowired
    private FileUtilities fileUtilities;


    @Value("${test.git.test_repo.url}")
    private String testRepoUrl;


    @Test
    public void addRepositorySuccessfully() throws Exception {
        AddRepositoryRequest addRepositoryRequest = new AddRepositoryRequest();
        addRepositoryRequest.setLanguage(Languages.JAVA);
        addRepositoryRequest.setUrl(testRepoUrl);

        ProjectDto actualProjectDto = extract(performPost("/project/repository/add", addRepositoryRequest)
            .andExpect(status().isOk()), ProjectDto.class);

        assertThat(actualProjectDto.getStatus(),
            equalTo(ProjectStatus.PROCESSING));
        assertThat(actualProjectDto.getName(),
            equalTo(fileUtilities.extractRepositoryName(testRepoUrl)));
    }

    @Test
    public void listRepositorySuccessfully() throws Exception {
//        clearAllClonedRepositories();
        //Given the project is added
        Integer sizeBeforeTest = extract(performGet("/project/list"), ProjectDto[].class)
            .length;
        AddRepositoryRequest addRepositoryRequest = new AddRepositoryRequest();
        addRepositoryRequest.setLanguage(Languages.JAVA);
        addRepositoryRequest.setUrl(testRepoUrl);
        ProjectDto actualProjectDto = extract(performPost("/project/repository/add", addRepositoryRequest)
            .andExpect(status().isOk()), ProjectDto.class);

        assertThat(actualProjectDto.getStatus(),
            equalTo(ProjectStatus.PROCESSING));
        assertThat(actualProjectDto.getName(),
            equalTo(fileUtilities.extractRepositoryName(testRepoUrl)));
        //When
        ProjectDto[] projectsListResponse =
            extract(performGet("/project/list"), ProjectDto[].class);
        //Then
        assertThat(projectsListResponse.length, equalTo(sizeBeforeTest + 1));
        assertThat(projectsListResponse[0].getName(),
            equalTo(fileUtilities.extractRepositoryName(testRepoUrl)));

    }

//    @Test()
//    public void getDeadCodeForNonAddedProjectFail() {
//        Map<String, String> urlVars = new HashMap<>();
//        urlVars.put("url", testRepoUrl);
//
//        testRestTemplate.getRestTemplate().setErrorHandler(new DefaultResponseErrorHandler() {
//            @Override
//            public void handleError(ClientHttpResponse response) throws IOException {
//                String body = IOUtils.toString(response.getBody());
//                Assert.assertTrue("wrong error message: " + body, body
//                    .contains(ProjectService.THIS_GIT_URL_NEED_TO_BE_ADDED));
//            }
//        });
//        ResponseEntity<ProjectDeadCodeDto> projectDeadCodeResponse =
//            testRestTemplate.getForEntity("/project/repository/dead-code/get?url=" + testRepoUrl,
//                ProjectDeadCodeDto.class);
//
//
////        System.out.println(projectDeadCodeResponse.getBody().toString());
//
//        //Then
//
//
//    }
}
