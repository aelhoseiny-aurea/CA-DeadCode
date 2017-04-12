package com.aurea.ca.deadcode;

import com.aurea.ca.deadcode.project.ProjectRepository;
import com.aurea.ca.deadcode.utilities.FileUtilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import reactor.spring.context.config.EnableReactor;

import java.io.File;
import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableReactor
@Transactional
public class CaDeadcodeApplicationTests {


    @Value("${git.clone.path}")
    private String testRepositoryDir;

    @Autowired
    private FileUtilities fileUtilities;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper mapper;


    private MockMvc mockMvc;

    @Test
    public void initializeContext() {

    }

    @Before
    public void buildMockMVC() {
        mockMvc = webAppContextSetup(webApplicationContext).build();

    }

    @Before
    public void clearAllClonedRepositories() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "project");
        projectRepository.deleteAll();
        File[] files = new File(testRepositoryDir).listFiles();
        if (files != null) {
            for (File file :
                files) {
                fileUtilities.deleteFolder(file);
            }
        }
    }

    @After
    public void tearDown() throws InterruptedException {

    }

    public MockMvc getMockMvc() {
        return mockMvc;
    }

    protected ResultActions performPost(String urlTemplate, Object addRepositoryRequest) throws Exception {
        return getMockMvc().perform(post(urlTemplate)
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(addRepositoryRequest)))
            .andDo(print());
    }

    protected ResultActions performGet(String urlTemplate) throws Exception {
        return performGet(urlTemplate, null);
    }

    protected ResultActions performGet(String urlTemplate, MultiValueMap<String, String> urlParams) throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get(urlTemplate);
        if (urlParams != null) {
            requestBuilder = requestBuilder.params(urlParams);
        }
        return getMockMvc().perform(requestBuilder)
            .andDo(print());
    }

    protected <T> T extract(ResultActions ra, Class<T> type) throws IOException {
        return mapper.readValue(ra.andReturn().getResponse().getContentAsString(), type);
    }

}
