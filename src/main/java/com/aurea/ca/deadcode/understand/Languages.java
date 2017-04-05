package com.aurea.ca.deadcode.understand;

/**
 * Created by ameen on 05/04/17.
 */
public enum Languages {
    JAVA("java"), C_SHARP("c#"), CPP("c++");

    private String languageName;


    Languages(String languageName) {
        this.languageName = languageName;
    }

    public String getForCommandLine() {
        return " -languages " + languageName;
    }


}
