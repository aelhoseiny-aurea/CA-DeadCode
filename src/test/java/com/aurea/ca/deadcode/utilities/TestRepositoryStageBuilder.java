package com.aurea.ca.deadcode.utilities;

import com.aurea.ca.deadcode.git.GitService;
import com.aurea.ca.deadcode.understand.Languages;
import com.aurea.ca.deadcode.understand.UnderstandService;
import com.aurea.ca.deadcode.understand.exceptions.CouldNotExecuteUnderstandCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Created by ameen on 07/04/17.
 */
@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TestRepositoryStageBuilder {

    @Autowired
    private GitService gitService;

    @Autowired
    private UnderstandService understandService;

    @Value("${test.git.test_repo.url}")
    private String testRepositoryUrl;

    private File dbFile;
    private File repositoryFolder;

    public File loadTestProject()
        throws InterruptedException, IOException, CouldNotExecuteUnderstandCommand, GitAPIException {
        this.cloneRepo().createDB().addRepoToDB();
        return dbFile;
    }

    public TestRepositoryStageBuilder cloneRepo() throws GitAPIException {
        repositoryFolder = gitService.clone(testRepositoryUrl);
        return this;
    }

    public TestRepositoryStageBuilder createDB()
        throws GitAPIException, InterruptedException, IOException, CouldNotExecuteUnderstandCommand {
        assert repositoryFolder != null;
        dbFile = understandService.createDataBase(repositoryFolder.getName(), Languages.JAVA);
        return this;
    }

    public TestRepositoryStageBuilder addRepoToDB()
        throws GitAPIException, InterruptedException, IOException, CouldNotExecuteUnderstandCommand {
        assert repositoryFolder != null;
        assert dbFile != null;
        understandService.addRepository(dbFile.getName(), repositoryFolder, Languages.JAVA);
        return this;
    }
}
