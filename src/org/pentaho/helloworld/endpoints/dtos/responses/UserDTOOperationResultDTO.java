package org.pentaho.helloworld.endpoints.dtos.responses;

import org.pentaho.helloworld.endpoints.dtos.entities.UserDTO;
import org.pentaho.helloworld.endpoints.dtos.responses.base.OperationResultDTO;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserDTOOperationResultDTO extends OperationResultDTO {

  //region Attributes
  public UserDTO user;
  //endregion
}
