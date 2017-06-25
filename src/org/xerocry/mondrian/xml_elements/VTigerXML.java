package org.xerocry.mondrian.xml_elements;

import org.xerocry.mondrian.Schema;
import org.xerocry.mondrian.mondrian_classes.*;

import java.util.ArrayList;
import java.util.HashMap;

public class VTigerXML {
    private ArrayList<ModuleXML> xmlModules = new ArrayList<>();
    HashMap<String, String> sourceInfo = new HashMap<>();


    /**
     * Основа формирования Mondrian схемы
     *
     * @param name - имя схемы
     * @return JAXB-объект содержащий массив кубов и вирт.кубов
     */
    public Schema generateSchema(String name) {
        Schema schema = new Schema(name);

        /*
        Для каждого модуля создаётся отдельный куб
         */
        for (ModuleXML module : xmlModules) {
            Cube cube = new Cube(module.getModuleName());
            cube.setTable(module.getFactTable());
            cube.setForeignKey(module.getKey());
            /*
            Сначала создаются измерения, которые будут включены в куб.
             */
            for (FieldXML field : module.getFieldXMLList()) {
                if (field.getUiType().equals("72")) {
                    Measure measure = new Measure(field.getFieldCaption());
                    measure.setAggregator("sum");
                    measure.setColumn(field.getColumnName());
                    cube.addMeasure(measure);
                    continue;
                }
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

        /*
        Затем виртуальный куб на основе относящихся к модулю модулям
         */
        for (Cube cube : schema.getCubes()) {
            VirtualCube virCube = new VirtualCube();
            if (cube.getRelated().size() == 0) {
                continue;
            }
            for (String related : cube.getRelated()) {
                if(schema.getCube(related) == null) continue;
                for (Dimension dimension : schema.getCube(related).getDimensions()) {
                    virCube.addVirtualDimension(new VirtualCubeDimension(dimension, related));
                }
                for (Measure measure : schema.getCube(related).getMeasures()) {
                    virCube.addVirtualMeasure(new VirtualCubeMeasure(measure.getName(), related));
                }
            }
            for (Dimension dimension : cube.getDimensions()) {
                virCube.addVirtualDimension(new VirtualCubeDimension(dimension, cube.getName()));
            }
            for (Measure measure : cube.getMeasures()) {
                virCube.addVirtualMeasure(new VirtualCubeMeasure(measure.getName(), cube.getName()));
            }
            virCube.setName(virCube.generateName(cube.getName()));
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
