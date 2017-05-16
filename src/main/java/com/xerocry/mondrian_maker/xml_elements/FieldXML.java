package com.xerocry.mondrian_maker.xml_elements;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldXML {
    private String columnName;
    private String fieldName;
    private String columnType;
    private String tableName;
    private String uiType;
    private String relatedModule;

    private static final String REL_MODULE_TYPE = "10";

    private boolean crmEntity;


    public boolean isRelated() {
        return uiType.equals(REL_MODULE_TYPE);
    }


}
