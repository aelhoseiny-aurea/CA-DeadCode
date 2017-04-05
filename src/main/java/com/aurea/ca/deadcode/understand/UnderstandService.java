package com.aurea.ca.deadcode.understand;

import java.io.File;
import java.io.IOException;

import com.aurea.ca.deadcode.understand.exceptions.CouldNotExecuteUnderstandCommand;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * Created by ameen on 05/04/17.
 */
@Service
public class UnderstandService {

    private static Logger logger = LoggerFactory.getLogger(UnderstandService.class);

    @Value("${understand.bin.dir}/und")
    private String understandCommand;

    @Value("${understand.database.dir}")
    private String understandDbLocation;

    public File createDataBase(String repositoryName, Languages language) throws IOException,
        InterruptedException, CouldNotExecuteUnderstandCommand {
        Runtime rt = Runtime.getRuntime();
        String databaseLocation = understandDbLocation + "/" + repositoryName + ".udb";
        String command = understandCommand + " create -db " + databaseLocation + language.getForCommandLine();
        Process process = rt.exec(command);
        process.waitFor();
        if (process.exitValue() != 0) {
            String errorMessage = IOUtils.toString(process.getErrorStream());
            logger.error(errorMessage);
            throw new CouldNotExecuteUnderstandCommand(command, errorMessage);
        }
        return new File(databaseLocation);
    }


}
