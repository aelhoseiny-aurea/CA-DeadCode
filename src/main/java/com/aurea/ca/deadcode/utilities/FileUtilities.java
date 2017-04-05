package com.aurea.ca.deadcode.utilities;

import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

import java.io.File;


/**
 * Created by ameen on 05/04/17.
 */
@Component
public class FileUtilities {

    public void deleteFolder(File folder) {
        assert FileSystemUtils.deleteRecursively(folder);
    }

    //url/repositorname.git
    public String extractRepositoryName(String testRepositoryUrl) {
        String[] urlParts = testRepositoryUrl.split("/");

        return urlParts[urlParts.length - 1].split(".git")[0];
    }
}
