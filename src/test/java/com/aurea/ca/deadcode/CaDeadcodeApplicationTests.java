package com.aurea.ca.deadcode;

import com.aurea.ca.deadcode.project.ProjectRepository;
import com.aurea.ca.deadcode.utilities.ConsumerWaitRule;
import com.aurea.ca.deadcode.utilities.FileUtilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;
import reactor.spring.context.config.EnableReactor;

import java.io.File;
import java.util.concurrent.Phaser;

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

    @Test
    public void initializeContext() {

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
}
