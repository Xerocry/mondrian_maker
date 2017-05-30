package com.xerocry.mondrian_maker.domain.model.entities.interfaces;

public interface IAddress {

  //region Properties
  String getStreetName();
  void setStreetName(String streetName);

  String getPostalCode();
  void setPostalCode(String postalCode);
  //endregion
}
