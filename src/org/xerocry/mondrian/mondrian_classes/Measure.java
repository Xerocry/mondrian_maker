package org.xerocry.mondrian.mondrian_classes;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class Measure {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String column;
    @XmlAttribute
    private String aggregator;
    @XmlAttribute
    private String formatString;

    public Measure() {
    }

    public Measure(String name) {
        this.name = name;
    }
}
