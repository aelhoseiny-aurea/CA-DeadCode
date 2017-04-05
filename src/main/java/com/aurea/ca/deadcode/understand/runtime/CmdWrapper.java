package com.aurea.ca.deadcode.understand.runtime;

import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by ameen on 05/04/17.
 */
@Component
public class CmdWarpper {

    public Process exec(String command) throws IOException, InterruptedException {
        Runtime rt = Runtime.getRuntime();
        Process process = rt.exec(command);
        process.waitFor();
        return process;
    }
}
