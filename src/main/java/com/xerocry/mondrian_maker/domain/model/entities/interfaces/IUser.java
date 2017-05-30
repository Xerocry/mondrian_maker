package com.xerocry.mondrian_maker.domain.model.entities.interfaces;

public interface IUser {

  //region Properties
  String getUserName();
  public String getPassword();
  public IAddress getAddress();
  public int getAge();
  //endregion
}
