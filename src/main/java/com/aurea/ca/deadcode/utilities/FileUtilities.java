package com.aurea.ca.deadcode.utilities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

import java.io.File;


/**
 * Created by ameen on 05/04/17.
 */
@Component
public class FileUtilities {

    private static final String DB_EXTENSION = ".udb";
    @Value("${git.clone.path}")
    private String repositoriesLocation;

    public void deleteFolder(File folder) {
        assert FileSystemUtils.deleteRecursively(folder);
    }

    //url/repositorname.git
    public String extractRepositoryName(String testRepositoryUrl) {
        String[] urlParts = testRepositoryUrl.split("/");

        return urlParts[urlParts.length - 1].split(".git")[0];
    }

    public File getRepositoryFolder(String projectName) {
        return new File(repositoriesLocation + File.separator + projectName);
    }

    public String getDataBaseName(String name) {
        return name + DB_EXTENSION;
    }
}
