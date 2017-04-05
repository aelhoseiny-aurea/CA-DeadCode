package com.aurea.ca.deadcode.scitools;

import com.aurea.ca.deadcode.CaDeadcodeApplicationTests;
import com.aurea.ca.deadcode.utilities.FileUtilities;
import com.scitools.understand.Database;
import com.scitools.understand.Understand;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Created by ameen on 05/04/17.
 */
public class UnderstandTest extends CaDeadcodeApplicationTests{

    @Value("${understand.bin.dir}")
    private String understandLocation;

    @Test
    public void testCreateDBUnderstandSuccess() throws IOException, InterruptedException {
        Runtime rt = Runtime.getRuntime();
        Process process = rt.exec(understandLocation + "/und create test.udb");
        process.waitFor();
        assertTrue(process.exitValue() == 0);
        File dbFile = new File("test.udb");
        assertTrue("no db file created", dbFile.exists());

        FileUtilities.deleteFolder(dbFile);
    }

    @Test
    public void testCreateDBAddAnalyseUnderstandSuccess() throws IOException, InterruptedException {
        Runtime rt = Runtime.getRuntime();
        Process process = rt.exec(understandLocation + "und create test.udb add ");
        process.waitFor();
        assertTrue(process.exitValue() == 0);
    }

}