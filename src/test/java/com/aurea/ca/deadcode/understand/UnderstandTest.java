package com.aurea.ca.deadcode.understand;

import com.aurea.ca.deadcode.CaDeadcodeApplicationTests;
import com.aurea.ca.deadcode.understand.exceptions.CouldNotExecuteUnderstandCommand;
import com.aurea.ca.deadcode.utilities.FileUtilities;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

/**
 * Created by ameen on 05/04/17.
 */
public class UnderstandTest extends CaDeadcodeApplicationTests {

    @Autowired
    private UnderstandService understandLocation;

    @Test
    public void testCreateDbUnderstandSuccess() throws IOException, InterruptedException, CouldNotExecuteUnderstandCommand {
        String repositoryFolder = "test";
        File dbFile = understandLocation.createDataBase(repositoryFolder, Languages.JAVA);
        assertTrue("no db file created", dbFile.exists());

        FileUtilities.deleteFolder(dbFile);
    }


    @Test
    public void testCreateDbAddAnalyseUnderstandSuccess() throws IOException, InterruptedException {

    }

}