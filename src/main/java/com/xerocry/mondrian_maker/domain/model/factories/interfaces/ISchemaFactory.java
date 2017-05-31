package com.xerocry.mondrian_maker.domain.model.factories.interfaces;


import java.io.FileNotFoundException;

/**
 * Created by raskia on 5/31/2017.
 */
public interface ISchemaFactory {

    String validSchema() throws FileNotFoundException;
}
