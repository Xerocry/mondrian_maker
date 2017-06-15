package org.xerocry.mondrian.mondrian_classes;


import org.xerocry.mondrian.Schema;
import org.xerocry.mondrian.xml_elements.FieldXML;
import org.xerocry.mondrian.xml_elements.ModuleXML;
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
    private String source;
    @XmlAttribute
    private String caption;
    @XmlTransient
    private String foreignKey;
    @XmlJavaTypeAdapter(TableAdapter.class)
    private String table;
    @XmlElement(name = "DimensionUsage", type = DimensionUsage.class)
    private List<DimensionUsage> dimensionUsages;
    @XmlTransient
    private List<String> related = new ArrayList<>();
    @XmlTransient
    private List<Dimension> dimensions;

    public Cube generate() {
        for (Dimension dimension : dimensions) {
            DimensionUsage dimensionGen = new DimensionUsage();
            dimensionGen = dimensionGen.generate(dimension);
            dimensionGen.setForeignKey(foreignKey);
            dimensionUsages.add(dimensionGen);
        }
        return this;
    }

    public Cube(String name) {
        dimensions = new ArrayList<>();
        dimensionUsages = new ArrayList<>();
        this.name = name;
    }

    public Cube() {
        dimensionUsages = new ArrayList<>();
        dimensions = new ArrayList<>();
    }

    public void addDimension(Dimension dimension) {
        dimensions.add(dimension);
    }
}
