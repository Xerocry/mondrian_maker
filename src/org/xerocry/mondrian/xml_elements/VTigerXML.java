package org.xerocry.mondrian.xml_elements;

import org.xerocry.mondrian.Schema;
import org.xerocry.mondrian.mondrian_classes.Cube;
import org.xerocry.mondrian.mondrian_classes.Dimension;
import org.xerocry.mondrian.mondrian_classes.VirtualCube;
import org.xerocry.mondrian.mondrian_classes.VirtualCubeDimension;

import java.util.ArrayList;
import java.util.HashMap;

public class VTigerXML {

    private final String DATE_TYPE = "date";
    private final String TIME_TYPE = "datetime";

    private ArrayList<ModuleXML> xmlModules = new ArrayList<>();
    HashMap<String, String> sourceInfo = new HashMap<>();


    public Schema generateSchema(String name) {
        Schema schema = new Schema(name);

        for (ModuleXML module : xmlModules) {
            Cube cube = new Cube(module.getModuleName());
            cube.setTable(module.getFactTable());
            cube.setForeignKey(module.getKey());
            for (FieldXML field : module.getFieldXMLList()) {
                Dimension dim;
                if (schema.getDimension(field.getFieldName()) != null) {
                    dim = new Dimension(cube.getName() + field.getFieldName(),
                            field.getFieldCaption()).generate(field);
                } else dim = new Dimension(field.getFieldName(), field.getFieldCaption()).generate(field);
                schema.addDimension(dim, cube.getName());
                cube.addDimension(dim);
            }
            cube.setRelated(module.getRelatedModules());
            cube.generate();
            schema.addCube(cube);
        }

        for (Cube cube : schema.getCubes()) {
            VirtualCube virCube = new VirtualCube();
            if (cube.getRelated().size() == 0) {
                continue;
            }
            for (String related : cube.getRelated()) {
                if(schema.getCube(related) == null) continue;
                for (Dimension dimension : schema.getCube(related).getDimensions()) {
                    virCube.addVirtualDimension(new VirtualCubeDimension(dimension, cube.getName()));
                }
            }
            virCube.setName(virCube.generateName());
            schema.addVirtualCube(virCube);
        }
        return schema;
    }

    void addModule(ModuleXML moduleXML) {
        xmlModules.add(moduleXML);
    }

    public HashMap<String, String> getSourceInfo() {
        return sourceInfo;
    }
}
