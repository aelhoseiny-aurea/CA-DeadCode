package com.aurea.ca.deadcode;

import com.aurea.ca.deadcode.project.ProjectRepository;
import com.aurea.ca.deadcode.utilities.FileUtilities;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import reactor.spring.context.config.EnableReactor;

import java.io.File;

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


    @Test
    public void initializeContext() {

    }

    @Before
    public void clearAllClonedRepositories() {
        projectRepository.deleteAll();
        File[] files = new File(testRepositoryDir).listFiles();
        if (files != null) {
            for (File file :
                files) {
                fileUtilities.deleteFolder(file);
            }
        }
    }
}
