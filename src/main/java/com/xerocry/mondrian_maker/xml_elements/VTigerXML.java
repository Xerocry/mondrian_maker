package com.xerocry.mondrian_maker.xml_elements;

import com.xerocry.mondrian_maker.domain.model.entities.Schema;
import com.xerocry.mondrian_maker.mondrian_classes.Cube;

import java.util.*;

/**
 * Created by raskia on 5/8/2017.
 */
public class VTigerXML {

    private final String DATE_TYPE = "date";
    private final String TIME_TYPE = "datetime";

    private ArrayList<ModuleXML> xmlModules = new ArrayList<>();

    public Schema generateSchema() {
        Schema schema = new Schema();

//        schema.getCubes().putAll(generateEmptyCubes());

        for (ModuleXML module : xmlModules) {
            Cube cube = new Cube(module.getModuleName());
            cube.setTable(module.getFactTable());
            cube.generate(module);
            schema.addCube(cube);
        }

        return schema;
    }

    public void addModule(ModuleXML moduleXML) {
        xmlModules.add(moduleXML);
    }

//    private HashMap<String, Cube> generateEmptyCubes() {
//        HashMap<String, Cube> cubes = new HashMap<>();
//        for (ModuleXML module : xmlModules) {
//            Cube cube = new Cube(module.getModuleName());
//            cube.setTable(module.getTable());
//            cubes.put(module.getModuleName(), cube);
//        }
//        return cubes;
//    }
}
