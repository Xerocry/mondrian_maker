package org.pentaho.helloworld.mondrian_classes;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class Level {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String column;
    @XmlTransient
    private String type;
    @XmlTransient
    private String levelType;

    @XmlAttribute
    private boolean visible = true;
    @XmlAttribute
    private boolean uniqueMembers;

    public Level() {
    }

    public Level(String name, String column, String type) {
        this.name = name;
        this.column = column;
        this.type = type;
    }
}
