package org.xerocry.mondrian.endpoints.dtos.responses;

import org.xerocry.mondrian.endpoints.dtos.responses.base.OperationResultDTO;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StringOperationResultDTO extends OperationResultDTO {

  //region Attributes
  public String string;
  //endregion
}
