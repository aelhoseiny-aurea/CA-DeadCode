package com.aurea.ca.deadcode.understand;

import com.aurea.ca.deadcode.CaDeadcodeApplicationTests;
import com.aurea.ca.deadcode.git.GitService;
import com.aurea.ca.deadcode.understand.exceptions.CouldNotExecuteUnderstandCommand;
import com.aurea.ca.deadcode.utilities.FileUtilities;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Created by ameen on 05/04/17.
 */
public class UnderstandTest extends CaDeadcodeApplicationTests {

    @Autowired
    private FileUtilities fileUtilities;

    @Autowired
    private UnderstandService understandService;

    @Autowired
    private GitService gitService;

    @Value("${test.git.test_repo.url}")
    private String testRepositoryUrl;


    @Test
    public void testCreateDbUnderstandSuccess() throws IOException, InterruptedException, CouldNotExecuteUnderstandCommand {
        String repositoryFolder = "test";
        File dbFile = understandService.createDataBase(repositoryFolder, Languages.JAVA);
        assertTrue("no db file created", dbFile.exists());

        fileUtilities.deleteFolder(dbFile);
    }


    @Test
    public void testCreateDbAddAnalyseUnderstandSuccess() throws IOException, InterruptedException, GitAPIException, CouldNotExecuteUnderstandCommand {
        //Given the repository folder is testRepositoryUrl:
        //And the repository was cloned suucessfully
        File repositoryFolder = gitService.clone(testRepositoryUrl);
        //When the und create called
        File dbFile = understandService.createDataBase(repositoryFolder.getName(), Languages.JAVA);
        //And the und add
        understandService.addRepository(dbFile, repositoryFolder, Languages.JAVA);
        //the und
        //Then

    }

}