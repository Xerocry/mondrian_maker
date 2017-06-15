package org.xerocry.mondrian.mondrian_classes;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class DimensionUsage {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String source;
    @XmlAttribute
    private String caption;
    @XmlAttribute
    private String foreignKey;

    public DimensionUsage generate(Dimension dimension) {
        this.name = dimension.getName();
        this.caption = dimension.getCaption();
        this.source = dimension.getName();
        return this;
    }
}


