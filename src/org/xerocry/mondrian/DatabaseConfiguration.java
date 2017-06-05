package org.xerocry.mondrian;

import org.xerocry.mondrian.xml_elements.XMLParser;
import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DatabaseConfiguration{
    private static DatabaseConfiguration instance;
    private static Schema schema;

        public static void init() throws JDOMException, IOException, ParserConfigurationException, SAXException {
        if (instance == null) {
            instance = new DatabaseConfiguration();
            schema = new XMLParser().readXML();
        }
    }

    public static DatabaseConfiguration getInstance() {
        return instance;
    }

    private DatabaseConfiguration() { }

    public static Schema getSchema() {
        return schema;
    }
}
