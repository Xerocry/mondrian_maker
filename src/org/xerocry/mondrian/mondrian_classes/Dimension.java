package org.xerocry.mondrian.mondrian_classes;

import org.xerocry.mondrian.xml_elements.FieldXML;
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

    @XmlAttribute
    private String type="StandardDimension";
    @XmlElement(name = "Hierarchy", type = Hierarchy.class)
    private List<Hierarchy> hierarchy = new ArrayList<>();


    public Dimension(String name) {
        this.name = name;
    }

    public Dimension() {

    }

    public Dimension generate(FieldXML field, String cubeFactTable) {
        this.name = field.getFieldName();
        Hierarchy hierarchy = new Hierarchy();
        hierarchy.setName(field.getFieldName());

        Level level = new Level();
        level.setColumn(field.getColumnName());
        level.setName(field.getFieldName());
        level.setType(field.getColumnType());
        hierarchy.addLevel(level);
        if (field.getTableName().equals("vtiger_crmentity")) {
            hierarchy.setTable("vtiger_crmentity");
        } else if(field.getTableName().equals(cubeFactTable)){
            hierarchy.setTable(null);
        } else hierarchy.setTable(field.getTableName());
        this.hierarchy.add(hierarchy);

        return this;
    }


}
