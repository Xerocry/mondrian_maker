package org.xerocry.mondrian;

import org.xerocry.mondrian.xml_elements.VTigerXML;
import org.xerocry.mondrian.xml_elements.XMLParser;
import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConfiguration {
    private static Logger log = Logger.getLogger(DatabaseConfiguration.class.getName());
    private static String vtigerSource;
    private static DatabaseConfiguration instance;
    private static Schema schema;

    /**
     * Метод, инициализирующий текущую конфигурацию базы данных.
     *
     * @param url - адрес домена с API Vtiger
     * @throws JDOMException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public static void init(String url) throws JDOMException, IOException, ParserConfigurationException, SAXException {
        if (instance == null) {
            instance = new DatabaseConfiguration(url);
            log.info("url = " + url + " in Config");
            VTigerXML vTigerXML = new XMLParser(vtigerSource).readXML();
            String sourceName = addJDBCResource(vTigerXML.getSourceInfo());
            schema = vTigerXML.generateSchema(sourceName);
        }
    }

    /**
     * Геттер для получения экземпляра класса для паттерна Синглтон.
     *
     * @return экземпляр синглтона
     */
    public static DatabaseConfiguration getInstance() {
        return instance;
    }

    private DatabaseConfiguration(String vtigerSource) {
        DatabaseConfiguration.vtigerSource = vtigerSource;
    }

    public static Schema getSchema() {
        return schema;
    }

    /**
     * Функция создания нового источника данных - БД.
     *
     * @return Имя подключения
     */
    public static String addJDBCResource(HashMap<String, String> sourceInfo) {
        String body = "{\"changed\": true, \"usingConnectionPool\": true, \"connectSql\": \"\", \"databaseName\": " +
                "\""+ sourceInfo.get("db_name") +"\", \"databasePort\": \""+ sourceInfo.get("db_port").substring(1) +
                "\", \"hostname\": \""+ sourceInfo.get("db_name") +"\", \"name\": \""+ sourceInfo.get("db_name") +
                "\", \"password\": \""+ sourceInfo.get("db_password") +"\", \"username\": \""+
                sourceInfo.get("db_username") +"\", \"attributes\": {}, \"connectionPoolingProperties\": {}, " +
                "\"extraOptions\": {}, \"accessType\": \"NATIVE\", \"databaseType\": {\"defaultDatabasePort\": 9001, " +
                "\"extraOptionsHelpUrl\": \"http://hsqldb.sourceforge.net/doc/guide/ch04.html#N109DA\", " +
                "\"name\": \"MySQL\", \"shortName\": \"MYSQL\", \"supportedAccessTypes\": " +
                "[\"NATIVE\", \"ODBC\", \"JNDI\"] } }";

        try {
            InetAddress ip;
            ip = InetAddress.getLocalHost();
            URL url = new URL("http://" + ip.getHostAddress() +
                    "/pentaho/plugin/data-access/api/datasource/jdbc/connection/TestDatasource");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("PUT");
            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());
            out.write(body);
            out.close();
            httpCon.getInputStream();
        } catch (Exception e) {
            log.log(Level.SEVERE, null, e);
        }
        return sourceInfo.get("db_name");
    }
}
