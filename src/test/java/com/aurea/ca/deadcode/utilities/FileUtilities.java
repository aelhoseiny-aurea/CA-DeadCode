package com.aurea.ca.deadcode.utilities;

import org.springframework.util.FileSystemUtils;

import java.io.File;

import static org.junit.Assert.assertTrue;

/**
 * Created by ameen on 05/04/17.
 */

public class FileUtilities {

    public static void deleteFolder(File folder) {
        assertTrue("failed to clean the folder", FileSystemUtils.deleteRecursively(folder));
    }
}
