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

    private boolean crmEntity;
    private String factTable;

    private boolean related;
    private ArrayList<String> relatedModules;

    public ModuleXML() {
        this.fieldXMLList = new ArrayList<>();
        this.relatedModules = new ArrayList<>();
    }

    public void addNewField(FieldXML field) {
        fieldXMLList.add(field);
    }

    public void addRelatedModule(String module) {
        relatedModules.add(module);
    }

}
