package org.xerocry.mondrian.mondrian_classes;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class VirtualCubeDimension {
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "caption")
    private String caption;
    @XmlAttribute(name = "cubeName")
    private String cubeName;

    public VirtualCubeDimension() {
    }

    public VirtualCubeDimension(Dimension dimension, String source) {
        this.name = dimension.getName();
        this.caption = dimension.getCaption();
        this.cubeName = source;
    }
}
