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

    private boolean crmEntity;


}
