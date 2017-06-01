package org.pentaho.helloworld.endpoints.dtos.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AddressDTO {

  //region Attributes
  public String streetName;
  public String postalCode;
  //endregion
}
