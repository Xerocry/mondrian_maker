package com.xerocry.mondrian_maker.endpoints.dtos.responses.base;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OperationResultDTO {

  //region Attributes
  public StatusMessageDTO statusMessage;
  //endregion

  //region Constructors
  public OperationResultDTO() {
    this.statusMessage = new StatusMessageDTO();
  }
  //endregion
}
