package com.xerocry.mondrian_maker.classes;

import com.xerocry.mondrian_maker.xml_elements.XMLParser;
import org.jdom2.JDOMException;

import java.io.IOException;

public class DatabaseConfiguration {

    private static DatabaseConfiguration instance;
    private static Schema schema;

    public static void init(String[] args) throws JDOMException, IOException {
        if (instance == null) {
            instance = new DatabaseConfiguration(args);
            schema = new XMLParser().readXML();
        }

    }

    public static DatabaseConfiguration getInstance() {
        return instance;
    }

    private DatabaseConfiguration(String[] args) {
    }

    public static Schema getSchema() {
        return schema;
    }
}
