package org.xerocry.mondrian.mondrian_classes;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class VirtualCube {
    @XmlElement(name = "VirtualCubeDimension", type = VirtualCubeDimension.class)
    private List<VirtualCubeDimension> dimensionUsages;
    @XmlElement(name = "VirtualCubeMeasure", type = VirtualCubeMeasure.class)
    private List<VirtualCubeMeasure> virMeasures;

    @XmlAttribute
    private String name;

    /**
     * Конструктор для JAXB
     */
    public VirtualCube() {
        dimensionUsages = new ArrayList<>();
        virMeasures = new ArrayList<>();
    }

    public void addVirtualDimension(VirtualCubeDimension dimension) {
        dimensionUsages.add(dimension);
    }

    public void addVirtualMeasure(VirtualCubeMeasure measure) {
        virMeasures.add(measure);
    }

    public String generateName(String cubeName) {
        return cubeName + "Virtual";
    }
}
