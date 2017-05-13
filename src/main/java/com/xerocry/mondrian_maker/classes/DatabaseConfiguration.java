package com.xerocry.mondrian_maker.classes;

import com.xerocry.mondrian_maker.xml_elements.XMLParser;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by raskia on 5/2/2017.
 */
public class DatabaseConfiguration {

    private static DatabaseConfiguration instance;
    private DbScheme scheme;

    public static void init(String[] args) throws JDOMException, IOException{
        if (instance == null) {
            instance = new DatabaseConfiguration(args);
            instance.scheme = new XMLParser().readXML();
        }

    }

    public static DatabaseConfiguration getInstance()  {
        return instance;
    }

    public DatabaseConfiguration(String[] args) {
    }

    public ArrayList<String> generateTest() throws IOException {
        return scheme.generateScheme();
    }
}
