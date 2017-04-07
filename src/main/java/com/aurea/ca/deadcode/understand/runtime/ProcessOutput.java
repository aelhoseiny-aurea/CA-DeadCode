package com.aurea.ca.deadcode.understand.runtime;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by ameen on 07/04/17.
 */
@Data
@AllArgsConstructor
public class ProcessOutput {
    private String output;
    private String error;
    private Process process;
}
