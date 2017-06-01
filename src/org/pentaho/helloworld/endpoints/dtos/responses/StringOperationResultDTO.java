package org.pentaho.helloworld.endpoints.dtos.responses;

import org.pentaho.helloworld.endpoints.dtos.responses.base.OperationResultDTO;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StringOperationResultDTO extends OperationResultDTO {

  //region Attributes
  public String string;
  //endregion
}
