package org.xerocry.mondrian.endpoints.dtos.responses.base;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StatusMessageDTO {

  //region Attributes
  public String code;
  public String message;
  //endregion
}
