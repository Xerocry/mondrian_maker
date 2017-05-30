package com.xerocry.mondrian_maker.domain.model.factories;


import com.xerocry.mondrian_maker.domain.model.entities.Address;
import com.xerocry.mondrian_maker.domain.model.entities.interfaces.IAddress;
import com.xerocry.mondrian_maker.domain.model.factories.interfaces.IAddressFactory;

public class AddressFactory implements IAddressFactory {

  @Override
  public IAddress create() {
    return new Address();
  }
}
