package org.xerocry.mondrian.xml_elements;

import org.xerocry.mondrian.DatabaseConfiguration;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class XMLParser {

    private static Logger log = Logger.getLogger(DatabaseConfiguration.class.getName());

    private static final String ROOT_CONFIGURATION_ELEMENT = "configurations";
    private static final String ROOT_MODULE_ELEMENT = "modules";
    private static final String ROOT_BLOCK_ELEMENT = "blocks";
    private static final String ROOT_FIELD_ELEMENT = "fields";
    private static final String ROOT_RELATED_MODULE_ELEMENT = "relatedmodules";
    private static final String ROOT_RELATED_LIST_ELEMENT = "relatedlists";
    private static final String ROOT_MODSTRINGS_ELEMENT = "modstrings";
    private static final String DESCRIPTION_ELEMENT = "description";



    private static final String CONFIGURATION_ELEMENT = "configuration";
    private static final String TABLENAME_ELEMENT = "tablename";
    private static final String FIELDNAME_ELEMENT = "fieldname";
    private static final String TRANSLATED_ELEMENT = "original";
    private static final String ORIGINAL_ELEMENT = "original";
    private static final String COLUMNNAME_ELEMENT = "columnname";
    private static final String COLUMTYPE_ELEMENT = "columntype";
    private static final String UI_TYPE_ELEMENT = "uitype";
    private static final String RELATED_MODULE_ELEMENT = "relatedmodule";
    private static final String MODULE_NAME_ELEMENT = "label";
    private static final String FIELD_LABEL_ELEMENT = "fieldlabel";
    private static final String ROOT_ENTITY_ID_ELEMENT = "entityidentifier";
    private static final String ENTITY_ID_ELEMENT = "entityidcolumn";

    private static final String CRM_TABLE_FIELD = "vtiger_crmentity";
    private static final String TRANSLATION_ELEMENT = "translation";

    private VTigerXML vTigerxML = new VTigerXML();

    private String url;

    public XMLParser(String url) {
        this.url = url;
    }

    public VTigerXML readXML() throws JDOMException, IOException, ParserConfigurationException, SAXException {
        URL u = new URL(url);
        log.info("Trying to connect to url " + url);
        URLConnection uc = u.openConnection();
        HttpURLConnection connection = (HttpURLConnection) uc;
        SAXBuilder builder = new SAXBuilder();
        InputStream in = connection.getInputStream();
        log.info("Input stream get try : " + in.available());
        Document document = builder.build(in);
        in.close();
        connection.disconnect();

        Element rootConfiguration = document.getRootElement();
        assert rootConfiguration.getName().equals(ROOT_CONFIGURATION_ELEMENT);
        Element configuration = rootConfiguration.getChild(CONFIGURATION_ELEMENT);
        assert configuration != null;

        Element description = configuration.getChild(DESCRIPTION_ELEMENT);
        String[] tmp = description.getValue().split(";");
        HashMap<String, String> sourceInfo = new HashMap<>();
        for (String var : tmp) {
            String[] keyVal = var.split("=");
            sourceInfo.put(keyVal[0].trim(), keyVal[1].trim());
        }
        vTigerxML.getSourceInfo().putAll(sourceInfo);
        parseXML(configuration);

        return vTigerxML;
    }

    private void parseXML(Element configuration) {
        Element modulesElement = configuration.getChild(ROOT_MODULE_ELEMENT);
        List<Element> modules = modulesElement.getChildren();

        for (Element moduleElement : modules) {
            System.out.println();
            ModuleXML moduleXML = parseModuleXML(moduleElement);
            vTigerxML.addModule(moduleXML);
        }

}
    private ModuleXML parseModuleXML(Element module) {
        ModuleXML moduleXML = new ModuleXML();
        List<Element> childNodes = module.getChildren();
        Element p1 = module.getParentElement();
        Element p2 = p1.getParentElement();
        Element p3 = p2.getChild(TRANSLATION_ELEMENT);
        Element mainTranslations = p3.getChild(ROOT_MODSTRINGS_ELEMENT);


        Element transNode = module.getChild(TRANSLATION_ELEMENT);
        Element modstringsNode = transNode.getChild(ROOT_MODSTRINGS_ELEMENT);

        for (Element childNode : childNodes) {
            if (childNode.getName().equals(MODULE_NAME_ELEMENT)) {
                String label;
                if((label = findTranslation(childNode.getValue(), mainTranslations)) != null){
                    moduleXML.setModuleCaption(label);
                } else moduleXML.setModuleCaption(childNode.getValue());
                moduleXML.setModuleName(childNode.getValue());
                continue;
            }
            if (childNode.getName().equals(ROOT_BLOCK_ELEMENT)) {
                List<Element> blocks = childNode.getChildren();
                for (Element block : blocks) {
                    if (!isContainFields(block)) {
                        continue;
                    }
                    Element fieldsElement = block.getChild(ROOT_FIELD_ELEMENT);
                    List<Element> fieldElements = fieldsElement.getChildren();

                    for (Element field : fieldElements) {
                        FieldXML fieldXML = new FieldXML();

                        if (!field.getChild(TABLENAME_ELEMENT).getValue().equals(CRM_TABLE_FIELD)) {
                            fieldXML.setTableName(field.getChild(TABLENAME_ELEMENT).getValue());
                            moduleXML.setFactTable(field.getChild(TABLENAME_ELEMENT).getValue());
                        } else {
                            fieldXML.setTableName(CRM_TABLE_FIELD);
                            fieldXML.setCrmEntity(true);
                        }

                        if (field.getChild(ROOT_ENTITY_ID_ELEMENT) != null) {
                            fieldXML.setPrimaryKey(field.getChild(ROOT_ENTITY_ID_ELEMENT)
                                    .getChild(ENTITY_ID_ELEMENT).getValue());
                            if (fieldXML.getTableName().equals(moduleXML.getFactTable())) {
                                moduleXML.setKey(field.getChild(ROOT_ENTITY_ID_ELEMENT)
                                        .getChild(ENTITY_ID_ELEMENT).getValue());
                            }
                        }

                        fieldXML.setColumnName(field.getChild(COLUMNNAME_ELEMENT).getValue());
                        fieldXML.setColumnType(field.getChild(COLUMTYPE_ELEMENT).getValue());
                        String fieldLabel;
                        if((fieldLabel = findTranslation(field.getChild(FIELD_LABEL_ELEMENT).getValue(), modstringsNode)) != null
                                || (fieldLabel = findTranslation(field.getChild(FIELD_LABEL_ELEMENT).getValue(), mainTranslations)) != null)
                        {
                            fieldXML.setFieldCaption(fieldLabel);
                        } else fieldXML.setFieldCaption(field.getChild(FIELDNAME_ELEMENT).getValue());
                        fieldXML.setFieldName(field.getChild(FIELDNAME_ELEMENT).getValue());
                        fieldXML.setUiType(field.getChild(UI_TYPE_ELEMENT).getValue());
                        if (fieldXML.isRelated()) {
                            Element related = field.getChild(ROOT_RELATED_MODULE_ELEMENT);
                            try {
                                List<Element> modules = related.getChildren(RELATED_MODULE_ELEMENT);
                                for (Element node : modules) {
                                    fieldXML.addRelatedModule(node.getValue());
                                }
                            } catch (NullPointerException e) {
                                fieldXML.addRelatedModule(moduleXML.getModuleName());
                            }
                        }
                        moduleXML.addNewField(fieldXML);
                    }
                }
            }

            if (childNode.getName().equals(ROOT_RELATED_LIST_ELEMENT)) {
                List<Element> relList = childNode.getChildren();
                for (Element relElement : relList) {
                    if(!relElement.getChild(RELATED_MODULE_ELEMENT).getValue().equals("")) {
                        moduleXML.addRelatedModule(relElement.getChild(RELATED_MODULE_ELEMENT).getValue());
                    }
                }
            }
        }
        return moduleXML;
    }


    private boolean isContainFields(Element element) {
        for (Element child : element.getChildren()) {
            if (child.getName().equals(ROOT_FIELD_ELEMENT)) {
                return true;
            }
        }
        return false;
    }

    private String findTranslation(String source, Element collection) {
        List<Element> modstrings = collection.getChildren();
        for (Element modstring : modstrings) {
            Element original = modstring.getChild(ORIGINAL_ELEMENT);
            if (original.getValue().equals(source)) {
                return modstring.getChild(TRANSLATED_ELEMENT).getValue();
            }
        }
        return null;
    }
}