package com.xerocry.mondrian_maker.mondrian_classes;


import com.xerocry.mondrian_maker.xml_elements.FieldXML;
import com.xerocry.mondrian_maker.xml_elements.ModuleXML;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class Cube {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String defaultMeasure;
    @XmlAttribute
    private String caption;
    @XmlJavaTypeAdapter(TableAdapter.class)
    private String table;
    @XmlElement(name = "Dimension", type = Dimension.class)
    private List<Dimension> dimension;

    public Cube generate(ModuleXML module) {
        for (FieldXML fieldXML : module.getFieldXMLList()) {
            Dimension dimensionGen = new Dimension(fieldXML.getFieldName());
            dimensionGen = dimensionGen.generate(fieldXML);
            dimensionGen.setForeignKey(fieldXML.getColumnName());
            dimension.add(dimensionGen);
        }
        return this;
    }

    public Cube(String name) {
        dimension = new ArrayList<>();
        this.name = name;
    }

    public Cube() {
        dimension = new ArrayList<>();
    }
}
