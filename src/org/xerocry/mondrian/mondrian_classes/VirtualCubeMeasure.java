package org.xerocry.mondrian.mondrian_classes;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class VirtualCubeMeasure {
    @XmlAttribute
    private String cubeName;
    @XmlAttribute
    private String name;

    public VirtualCubeMeasure() {
    }

    public VirtualCubeMeasure(String name, String cubeName) {
        this.name = "[Measures].[" + name + "]";
        this.cubeName = cubeName;
    }
}
