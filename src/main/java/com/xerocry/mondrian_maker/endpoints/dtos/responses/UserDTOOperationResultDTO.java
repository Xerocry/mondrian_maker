package com.xerocry.mondrian_maker.endpoints.dtos.responses;

import com.xerocry.mondrian_maker.endpoints.dtos.entities.UserDTO;
import com.xerocry.mondrian_maker.endpoints.dtos.responses.base.OperationResultDTO;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserDTOOperationResultDTO extends OperationResultDTO {

  //region Attributes
  public UserDTO user;
  //endregion
}
