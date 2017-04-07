package com.aurea.ca.deadcode;

import com.aurea.ca.deadcode.utilities.FileUtilities;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CaDeadcodeApplicationTests {

    @Value("${git.clone.path}")
    private String testRepositoryDir;

    @Autowired
    private FileUtilities fileUtilities;


    @Test
    public void initializeContext() {

    }

    @Before
    public void clearAllClonedRepositories() {
        File[] files = new File(testRepositoryDir).listFiles();
        if (files != null) {
            for (File file :
                files) {
                fileUtilities.deleteFolder(file);
            }
        }
    }
}
