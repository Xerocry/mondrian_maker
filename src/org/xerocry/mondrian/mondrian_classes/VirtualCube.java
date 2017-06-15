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

    @XmlAttribute
    private String name;

    public VirtualCube() {
        dimensionUsages = new ArrayList<>();
    }

    public void addVirtualDimension(VirtualCubeDimension dimension) {
        dimensionUsages.add(dimension);
    }

    public String generateName() {
        VirtualCubeDimension dim = dimensionUsages.get(1);
        return dim.getCaption() + "Virtual";
    }
}
