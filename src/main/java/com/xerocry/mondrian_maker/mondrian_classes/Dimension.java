package com.xerocry.mondrian_maker.mondrian_classes;

import com.xerocry.mondrian_maker.xml_elements.FieldXML;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@XmlRootElement(name="Dimension")
public class Dimension {
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute
    private String foreignKey;
    @XmlJavaTypeAdapter(TableAdapter.class)
    private String table;
    @XmlAttribute
    private String type="StandardDimension";
    @XmlElement(name = "Hierarchy", type = Hierarchy.class)
    private List<Hierarchy> hierarchy = new ArrayList<>();


    public Dimension(String name) {
        this.name = name;
    }

    public Dimension() {

    }

    public Dimension generate(FieldXML field) {
        this.name = field.getFieldName();
        Hierarchy hierarchy = new Hierarchy();
        hierarchy.setName(field.getFieldName());
        Level level = new Level();
        level.setColumn(field.getColumnName());
        level.setName(field.getFieldName());
        level.setType(field.getColumnType());
        hierarchy.addLevel(level);
        if (field.getTableName().equals("vtiger_crmentity")) {
            this.table = "vtiger_crmentity";
        }
        this.hierarchy.add(hierarchy);

        return this;
    }


}
