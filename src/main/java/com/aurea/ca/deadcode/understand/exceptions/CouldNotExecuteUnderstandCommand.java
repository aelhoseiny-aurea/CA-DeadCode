package com.aurea.ca.deadcode.understand.exceptions;

/**
 * Created by ameen on 05/04/17.
 */
public class CouldNotExecuteUnderstandCommand extends Exception {
    public CouldNotExecuteUnderstandCommand(String command, String errorMessage) {
        super("command: " + command + " \r\n" + errorMessage);
    }
}
