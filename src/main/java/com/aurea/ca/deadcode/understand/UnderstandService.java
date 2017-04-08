package com.aurea.ca.deadcode.understand;

import com.aurea.ca.deadcode.project.Project;
import com.aurea.ca.deadcode.understand.exceptions.CouldNotExecuteUnderstandCommand;
import com.aurea.ca.deadcode.understand.runtime.CmdWrapper;
import com.aurea.ca.deadcode.understand.runtime.ProcessOutput;
import com.aurea.ca.deadcode.utilities.FileUtilities;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by ameen on 05/04/17.
 */
@Service
public class UnderstandService {

    @Value("${understand.bin.dir}/und")
    private String understandCommand;

    @Value("${understand.bin.dir}")
    private String understandBinLocation;

    @Value("${understand.database.dir}")
    private String understandDbLocation;

    @Value("${understand.python.bin.dir}")
    private String pythonBinDir;

    @Value("${understand.python.scripts.dir}/understand_variables.py")
    private String getVariablesDataScript;

    @Autowired
    private FileUtilities fileUtilities;

    @Autowired
    private CmdWrapper cmd;

    public File createDataBase(String repositoryName, Languages language) throws IOException,
        InterruptedException, CouldNotExecuteUnderstandCommand {
        String databaseLocation = understandDbLocation + "/" + repositoryName + ".udb";
        String command = understandCommand + " create -db " + databaseLocation + language.getForCommandLine();
        cmd.exec(command);
        return new File(databaseLocation);
    }


    public void addRepository(String dbName, File repositoryFolder, Languages java)
        throws IOException, InterruptedException, CouldNotExecuteUnderstandCommand {
        String databaseLocation = understandDbLocation + "/" + dbName;
        String command = understandCommand + " add " + repositoryFolder.getAbsolutePath() + " " + databaseLocation;
        cmd.exec(command);
    }

    public void analysis(String dbName) throws InterruptedException, IOException, CouldNotExecuteUnderstandCommand {
        String databaseLocation = understandDbLocation + "/" + dbName;
        String command = understandCommand + " analyze " + databaseLocation;
        cmd.exec(command);

    }

    public List<VariableReference> getVariablesReferences(String dbName) throws InterruptedException, IOException, CouldNotExecuteUnderstandCommand {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        String databaseLocation = understandDbLocation + "/" + dbName;
        String command = pythonBinDir + " " + getVariablesDataScript + " " + databaseLocation;
        ProcessOutput processOutput = cmd.exec(command);
        List<VariableReference> allVariablesReferences = new ArrayList<>();
        for (String line : processOutput.getOutput().split("\\r")) {
            if (line.length() > 1) {
                VariableReference variableReferences = objectMapper.readValue(line, VariableReference.class);
                allVariablesReferences.add(variableReferences);
            }
        }

        return allVariablesReferences;
    }

    public void populateDeadCode(Project project)
        throws InterruptedException, IOException, CouldNotExecuteUnderstandCommand {
        File database = this.createDataBase(project.getName(), project.getLanguage());
        this.addRepository(database.getName(), fileUtilities.getRepositoryFolder(project.getName()),
            project.getLanguage());
        String dataBaseName = fileUtilities.getDataBaseName(project.getName());
        this.analysis(dataBaseName);
        this.getVariablesReferences(dataBaseName);
    }
}
