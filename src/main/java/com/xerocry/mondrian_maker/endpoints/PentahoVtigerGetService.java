package com.xerocry.mondrian_maker.endpoints;

import com.xerocry.mondrian_maker.domain.model.entities.interfaces.IUser;
import com.xerocry.mondrian_maker.domain.services.interfaces.IRDO;
import com.xerocry.mondrian_maker.endpoints.dtos.mappers.interfaces.IUserDTOMapper;
import com.xerocry.mondrian_maker.endpoints.dtos.responses.StringOperationResultDTO;
import com.xerocry.mondrian_maker.endpoints.dtos.responses.UserDTOListOperationResultDTO;
import com.xerocry.mondrian_maker.endpoints.dtos.responses.UserDTOOperationResultDTO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path( "@plugin.java.rest.path.root@" )
public class PentahoVtigerGetService {

  //region Attributes
  private IRDO RDO;
  private IUserDTOMapper userDTOMapper;
  //endregion

  @Autowired
  public PentahoVtigerGetService(IRDO rdo, IUserDTOMapper userDTOMapper ) {

    //dependency obtained via constructor dependency injection from spring framework
    this.RDO = rdo;
    this.userDTOMapper = userDTOMapper;
  }

  @GET
  @Path( "/hello" )
  @Produces( MediaType.APPLICATION_JSON )
  public StringOperationResultDTO hello() {

    //create result DTO
    StringOperationResultDTO result = new StringOperationResultDTO();

    //fill string
    result.string = "Hello World from Pentaho Service!";

    //fill status message
    result.statusMessage.code = "OK_CODE";
    result.statusMessage.message = "OK_MESSAGE";

    //return result DTO
    return result;
  }

  @GET
  @Path( "/users" )
  @Produces( MediaType.APPLICATION_JSON )
  public UserDTOListOperationResultDTO getUsers() {

    //get users from the domain model
    Iterable<IUser> users = this.RDO.getUserService().getUsers();

    //transform users to DTOs for serialization
    UserDTOListOperationResultDTO result = new UserDTOListOperationResultDTO();

    //fill users
    result.users = this.userDTOMapper.toDTOs( users );

    //fill status message
    result.statusMessage.code = "OK_CODE";
    result.statusMessage.message = "OK_MESSAGE";

    //return result DTO
    return result;
  }

  @GET
  @Path( "/user/{userName}" )
  @Produces( MediaType.APPLICATION_JSON )
  public UserDTOOperationResultDTO getUser( @PathParam( "userName" ) String userName ) {

    //get user from the domain model
    IUser user = this.RDO.getUserService().getUser( userName );

    //transform users to DTOs for serialization
    UserDTOOperationResultDTO result = new UserDTOOperationResultDTO();

    //fill string
    result.user = this.userDTOMapper.toDTO( user );

    //fill status message
    result.statusMessage.code = "OK_CODE";
    result.statusMessage.message = "OK_MESSAGE";

    //return result DTO
    return result;
  }
}
