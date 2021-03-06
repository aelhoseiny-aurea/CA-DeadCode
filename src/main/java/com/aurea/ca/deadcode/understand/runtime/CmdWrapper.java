package com.aurea.ca.deadcode.understand.runtime;

import com.aurea.ca.deadcode.understand.exceptions.CouldNotExecuteUnderstandCommand;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by ameen on 05/04/17.
 */
@Component
public class CmdWrapper {

    private static Logger logger = LoggerFactory.getLogger(CmdWrapper.class);

    public ProcessOutput exec(String command) throws IOException, InterruptedException, CouldNotExecuteUnderstandCommand {
        logger.info(command);
        Runtime rt = Runtime.getRuntime();
        Process process = rt.exec(command);
        process.waitFor();
        String output = IOUtils.toString(process.getInputStream());
        logger.debug(output);

        String errorMessage = IOUtils.toString(process.getErrorStream());
        if (process.exitValue() != 0) {
            logger.error(errorMessage);
            throw new CouldNotExecuteUnderstandCommand(command, errorMessage);
        }
        return new ProcessOutput(output,errorMessage,process);
    }

}
