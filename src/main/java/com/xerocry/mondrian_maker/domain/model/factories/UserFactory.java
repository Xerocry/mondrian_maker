package com.xerocry.mondrian_maker.domain.model.factories;

import com.xerocry.mondrian_maker.domain.model.entities.User;
import com.xerocry.mondrian_maker.domain.model.entities.interfaces.IAddress;
import com.xerocry.mondrian_maker.domain.model.entities.interfaces.IUser;
import com.xerocry.mondrian_maker.domain.model.factories.interfaces.IAddressFactory;
import com.xerocry.mondrian_maker.domain.model.factories.interfaces.IUserFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class UserFactory implements IUserFactory {

  private IAddressFactory addressFactory;

  @Autowired
  public UserFactory( IAddressFactory addressFactory ) {
    this.addressFactory = addressFactory;
  }

  @Override
  public IUser create( String userName, String password, IAddress address, int age ) {
    return new User( userName, password, address, age );
  }

  @Override
  public IUser create( String userName, String password, String addressStreetName, String addressPostalCode,
                              int age ) {
    IAddress address = this.addressFactory.create();
    address.setStreetName( addressStreetName );
    address.setPostalCode( addressPostalCode );
    return new User( userName, password, address, age );
  }
}
