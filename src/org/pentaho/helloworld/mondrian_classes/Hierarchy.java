package org.pentaho.helloworld.mondrian_classes;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class Hierarchy {
    @XmlElement(name = "Level", type = Level.class)
    private List<Level> levels = new ArrayList<>();
    @XmlTransient
    private final String DATE_TYPE = "date";
    @XmlTransient
    private final String TIME_TYPE = "datetime";
    @XmlAttribute
    private String name;
    @XmlAttribute
    private boolean visible=true;
    @XmlAttribute
    private boolean hasAll=true;


    public Hierarchy() {
        this.levels = new ArrayList<>();
    }

    public void addLevel(Level level) {
        if (!levels.contains(level)) {
            levels.add(level);
        }
    }

}
