package com.xerocry.mondrian_maker.domain.model.factories.interfaces;

import com.xerocry.mondrian_maker.domain.model.entities.interfaces.IAddress;
import com.xerocry.mondrian_maker.domain.model.entities.interfaces.IUser;

public interface IUserFactory {
  IUser create(String userName, String password, IAddress address, int age);
  IUser create(String userName, String password, String addressStreetName, String addressPostalCode, int age);
}
