package org.xerocry.mondrian;

import org.xerocry.mondrian.mondrian_classes.Cube;
import lombok.Getter;
import lombok.Setter;
import org.xerocry.mondrian.mondrian_classes.Dimension;
import org.xerocry.mondrian.mondrian_classes.VirtualCube;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name="Schema")
@XmlAccessorType(XmlAccessType.FIELD)
public class Schema{
    @XmlAttribute
    private String name = "firstVer";

    @XmlElement(name = "Dimension", type = Dimension.class)
    private List<Dimension> dimensions = new ArrayList<>();

    @XmlElement(name = "Cube", type = Cube.class)
    private List<Cube> cubes = new ArrayList<>();

    @XmlElement(name = "VirtualCube", type = VirtualCube.class)
    private List<VirtualCube> virtualCubes = new ArrayList<>();

    public void addCube(Cube cube) {
        cubes.add(cube);
    }

    public Cube getCube(String name) {
        for (Cube cube : cubes) {
            if (cube.getName().equals(name)) {
                return cube;
            }
        }
        return null;
    }

    public void addDimension(Dimension dimension, String cubeName) {
        dimensions.add(dimension);
    }

    public void addVirtualCube(VirtualCube cube) {
        virtualCubes.add(cube);
    }

    public Schema(String name) {
        this.name = name;
    }

    /**
     * Конструктор для JAXB
     */
    public Schema() {
    }

    public Dimension getDimension(String name) {
        for (Dimension dim : dimensions) {
            if (dim.getName().equals(name)) {
                return dim;
            }
        }
        return null;
    }
}
