package org.pentaho.helloworld.endpoints.dtos.responses;

import org.pentaho.helloworld.endpoints.dtos.entities.UserDTO;
import org.pentaho.helloworld.endpoints.dtos.responses.base.OperationResultDTO;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class UserDTOListOperationResultDTO extends OperationResultDTO {

  //region Attributes
  public List<UserDTO> users;
  //endregion
}
