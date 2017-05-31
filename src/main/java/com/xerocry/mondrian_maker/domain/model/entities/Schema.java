package com.xerocry.mondrian_maker.domain.model.entities;

import com.xerocry.mondrian_maker.domain.model.entities.interfaces.ISchema;
import com.xerocry.mondrian_maker.mondrian_classes.Cube;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name="Schema")
@XmlAccessorType(XmlAccessType.FIELD)
public class Schema implements ISchema{
    @XmlAttribute
    private String name = "firstVer";

    @XmlElement(name = "Cube", type = Cube.class)
    private List<Cube> cubes = new ArrayList<>();

    @Override
    public void addCube(Cube cube) {
        cubes.add(cube);
    }
}
