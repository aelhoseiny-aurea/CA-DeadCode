package com.aurea.ca.deadcode.git;

import com.aurea.ca.deadcode.CaDeadcodeApplicationTests;
import com.aurea.ca.deadcode.utilities.FileUtilities;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;

import static org.junit.Assert.assertTrue;

/**
 * Created by ameen on 04/04/17.
 */
public class GitServiceTest extends CaDeadcodeApplicationTests {

    @Value("${test.git.test_repo.url}")
    private String testRepoUrl;


    @Autowired
    private FileUtilities fileUtilities;

    @Autowired
    private GitService gitService;

    @Test
    public void clonePublicRepositorySuccess() throws GitAPIException {
        //Given
        String testRepositoryName = "testrepo";
        //When

        File clonedRepository = gitService.clone(testRepoUrl);
        //Then
        assertTrue("Failed to clone !- folder does not exist", clonedRepository.exists());
        //tear down
        fileUtilities.deleteFolder(clonedRepository);
    }


}
