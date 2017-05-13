package com.xerocry.mondrian_maker.xml_elements;

import com.xerocry.mondrian_maker.classes.DbScheme;

import java.util.ArrayList;

/**
 * Created by raskia on 5/8/2017.
 */
public class VTigerXML {

    private ArrayList<ModuleXML> xmlModules = new ArrayList<>();

    public DbScheme generateScheme() {
        return new DbScheme();
    }

    public void addModule(ModuleXML moduleXML) {
        xmlModules.add(moduleXML);
    }
}
