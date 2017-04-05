package com.aurea.ca.deadcode.understand;

import com.aurea.ca.deadcode.understand.exceptions.CouldNotExecuteUnderstandCommand;
import com.aurea.ca.deadcode.understand.runtime.CmdWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;


/**
 * Created by ameen on 05/04/17.
 */
@Service
public class UnderstandService {

    @Value("${understand.bin.dir}/und")
    private String understandCommand;

    @Value("${understand.database.dir}")
    private String understandDbLocation;

    @Autowired
    private CmdWrapper cmd;

    public File createDataBase(String repositoryName, Languages language) throws IOException,
        InterruptedException, CouldNotExecuteUnderstandCommand {
        String databaseLocation = understandDbLocation + "/" + repositoryName + ".udb";
        String command = understandCommand + " create -db " + databaseLocation + language.getForCommandLine();
        Process process = cmd.exec(command);
        return new File(databaseLocation);
    }


    public void addRepository(File dbFile, File repositoryFolder, Languages java) throws IOException, InterruptedException, CouldNotExecuteUnderstandCommand {
        String databaseLocation = understandDbLocation + "/" + repositoryFolder.getName() + ".udb";
        String command = understandCommand + " add " + repositoryFolder.getAbsolutePath() + " " + databaseLocation;
        cmd.exec(command);
    }
}
