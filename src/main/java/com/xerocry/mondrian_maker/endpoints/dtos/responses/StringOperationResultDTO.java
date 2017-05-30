package com.xerocry.mondrian_maker.endpoints.dtos.responses;

import com.xerocry.mondrian_maker.endpoints.dtos.responses.base.OperationResultDTO;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StringOperationResultDTO extends OperationResultDTO {

  //region Attributes
  public String string;
  //endregion
}
