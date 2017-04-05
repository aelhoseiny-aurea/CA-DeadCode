package com.aurea.ca.deadcode.git;

import com.aurea.ca.deadcode.utilities.FileUtilities;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by ameen on 04/04/17.
 */
@Service
public class GitService {

    @Value("${git.clone.path}")
    private String clonePath;

    @Autowired
    private FileUtilities fileUtilities;

    public File clone(String repoUrl) throws GitAPIException {
        String testRepositoryPath = clonePath + File.separator + fileUtilities.extractRepositoryName(repoUrl);
        Git git = Git.cloneRepository()
            .setURI(repoUrl)
            .setDirectory(new File(testRepositoryPath)).call();
        return new File(testRepositoryPath);
    }
}
