package org.xerocry.mondrian.mondrian_classes;

import org.xerocry.mondrian.xml_elements.FieldXML;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
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
    private String caption;
    @XmlAttribute
    private String type="StandardDimension";
    @XmlElement(name = "Hierarchy", type = Hierarchy.class)
    private List<Hierarchy> hierarchy = new ArrayList<>();


    public Dimension(String name, String caption) {
        this.name = name;
        this.caption = caption;
    }

    public Dimension() {
    }

    public Dimension(Dimension other) {
        this.name = other.name;
        this.caption = other.caption;
        this.type = other.type;
        this.hierarchy = other.hierarchy;
    }

    public Dimension generate(FieldXML field) {
        Hierarchy hierarchy = new Hierarchy();
        hierarchy.setName(field.getFieldName());

        Level level = new Level();
        level.setColumn(field.getColumnName());
        level.setName(field.getFieldName());
        level.setCaption(field.getFieldCaption());
        level.setType(field.getColumnType());
        hierarchy.addLevel(level);
        if (field.getTableName().equals("vtiger_crmentity")) {
            hierarchy.setTable("vtiger_crmentity");
        } else hierarchy.setTable(field.getTableName());
        hierarchy.setCaption(field.getFieldCaption());
        hierarchy.setPrimaryKey(field.getPrimaryKey());
        this.hierarchy.add(hierarchy);

        return this;
    }




}
