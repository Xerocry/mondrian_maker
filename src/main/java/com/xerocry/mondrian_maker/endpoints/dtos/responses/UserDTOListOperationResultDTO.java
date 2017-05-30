package com.xerocry.mondrian_maker.endpoints.dtos.responses;

import com.xerocry.mondrian_maker.endpoints.dtos.entities.UserDTO;
import com.xerocry.mondrian_maker.endpoints.dtos.responses.base.OperationResultDTO;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class UserDTOListOperationResultDTO extends OperationResultDTO {

  //region Attributes
  public List<UserDTO> users;
  //endregion
}
