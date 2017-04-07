package com.aurea.ca.deadcode.understand;

import com.aurea.ca.deadcode.CaDeadcodeApplicationTests;
import com.aurea.ca.deadcode.understand.exceptions.CouldNotExecuteUnderstandCommand;
import com.aurea.ca.deadcode.utilities.FileUtilities;
import com.aurea.ca.deadcode.utilities.TestRepositoryStageBuilder;
import com.scitools.understand.Understand;
import com.scitools.understand.UnderstandException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
    private TestRepositoryStageBuilder testRepositoryStageBuilder;

    @Value("${test.git.test_repo.url}")
    private String testRepositoryUrl;

    @Value("${git.clone.path}")
    private String testRepositoryDir;


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
        File dbFile = testRepositoryStageBuilder.loadTestProject();
        //the und analysis
        understandService.analysis(dbFile.getName());
        //Then
        //Tear down
        fileUtilities.deleteFolder(dbFile);
    }

//    enum Kinds{
//        Package,Parameter,Public Class,Public Static Method,Variable,Unresolved Type,File
//    }

    @Test
    public void testGetAllVariablesReferences() throws InterruptedException, CouldNotExecuteUnderstandCommand, GitAPIException, IOException, UnderstandException {
        Understand.loadNativeLibrary();
        File dbFile = testRepositoryStageBuilder.loadTestProject();
        understandService.analysis(dbFile.getName());
        List<VariableReference> allVariablesReferences = understandService.getVariablesReferences(dbFile.getName());
    }


}