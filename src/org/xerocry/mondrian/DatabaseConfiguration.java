package org.xerocry.mondrian;

import org.xerocry.mondrian.xml_elements.VTigerXML;
import org.xerocry.mondrian.xml_elements.XMLParser;
import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DatabaseConfiguration{
    private static String vtigerSource;
    private static DatabaseConfiguration instance;
    private static Schema schema;

        public static void init(String url) throws JDOMException, IOException, ParserConfigurationException, SAXException {
        if (instance == null) {
            instance = new DatabaseConfiguration(url);
            VTigerXML vTigerXML = new XMLParser().readXML(vtigerSource);
            schema = vTigerXML.generateSchema();
        }
    }

    public static DatabaseConfiguration getInstance() {
        return instance;
    }

    private DatabaseConfiguration(String vtigerSource) {
        DatabaseConfiguration.vtigerSource = vtigerSource;
    }

    public static Schema getSchema() {
        return schema;
    }
}
