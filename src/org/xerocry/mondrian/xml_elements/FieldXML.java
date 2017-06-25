package org.xerocry.mondrian.xml_elements;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class FieldXML {
    private String columnName;
    private String fieldName;
    private String columnType;
    private String tableName;
    private String fieldCaption;
    private String uiType;
    private String primaryKey;
    private ArrayList<String> relatedModules = new ArrayList<>();

    private static final String REL_MODULE_TYPE = "10";

    private boolean crmEntity;

    public void addRelatedModule(String module) {
        relatedModules.add(module);
    }

    public boolean isRelated() {
        return uiType.equals(REL_MODULE_TYPE);
    }


}
