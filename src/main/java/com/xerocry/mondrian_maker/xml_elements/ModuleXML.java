package com.xerocry.mondrian_maker.xml_elements;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class ModuleXML {
    private String moduleName;
    private Integer sequenceNum;
    private ArrayList<FieldXML> fieldXMLList;

    private boolean related;
    private boolean crmEntity;
    private ArrayList<ModuleXML> relatedModules;

    public ModuleXML() {
        fieldXMLList = new ArrayList<>();
        relatedModules = new ArrayList<>();
    }

    public void addNewField(FieldXML field) {
        fieldXMLList.add(field);
    }



}
