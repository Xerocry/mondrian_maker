package com.xerocry.mondrian_maker.domain.model.entities.interfaces;

import com.xerocry.mondrian_maker.mondrian_classes.Cube;

/**
 * Created by raskia on 5/31/2017.
 */
public interface ISchema {
    void addCube(Cube cube);
}
