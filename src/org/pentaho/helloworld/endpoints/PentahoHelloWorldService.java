package org.pentaho.helloworld.endpoints;

import org.jdom2.JDOMException;
import org.pentaho.helloworld.DatabaseConfiguration;
import org.pentaho.helloworld.Directories;
import org.pentaho.helloworld.Schema;
import org.pentaho.helloworld.endpoints.dtos.responses.StringOperationResultDTO;
import org.pentaho.helloworld.endpoints.dtos.responses.UserDTOListOperationResultDTO;
import org.pentaho.helloworld.endpoints.dtos.responses.UserDTOOperationResultDTO;
import org.pentaho.helloworld.endpoints.dtos.mappers.interfaces.IUserDTOMapper;
import org.pentaho.helloworld.domain.model.entities.interfaces.IUser;
import org.pentaho.helloworld.domain.services.interfaces.IRDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXException;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringWriter;

@Path( "@plugin.java.rest.path.root@" )
public class PentahoHelloWorldService {

  //region Attributes
  private IRDO RDO;
  private IUserDTOMapper userDTOMapper;
  //endregion

  @Autowired
  public PentahoHelloWorldService( IRDO rdo, IUserDTOMapper userDTOMapper ) {

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
  @Path( "/mondrian" )
  @Produces(MediaType.APPLICATION_XML)
  public String mondrian( @PathParam( CATALOG_ID ) String catalog, // Optional
                          @FormDataParam( UPLOAD_ANALYSIS ) InputStream uploadAnalysis,
                          @FormDataParam( UPLOAD_ANALYSIS ) FormDataContentDisposition schemaFileInfo,
                          @FormDataParam( ORIG_CATALOG_NAME ) String origCatalogName, // Optional
                          @FormDataParam( DATASOURCE_NAME ) String datasourceName, // Optional
                          @FormDataParam( OVERWRITE_IN_REPOS ) Boolean overwrite,
                          @FormDataParam( XMLA_ENABLED_FLAG ) Boolean xmlaEnabledFlag, @FormDataParam( PARAMETERS ) String parameters,
                          @FormDataParam( DATASOURCE_ACL ) RepositoryFileAclDto acl )
  ) throws ParserConfigurationException, SAXException, IOException, JDOMException {

    DatabaseConfiguration.init();


    Schema schema = DatabaseConfiguration.getSchema();

    StringWriter stringWriter = new StringWriter();
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(Schema.class);
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

      // output pretty printed
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

      jaxbMarshaller.marshal(schema, System.out);
      jaxbMarshaller.marshal(schema, stringWriter);
//            if (scheme.size() == 0) {
//                throw new Exception("Dump has 0 modules");
//            }
//            for (String s : DatabaseConfiguration.getInstance()) {
//                outFilePrintWriter.println(s);
//            }

    } catch (JAXBException e) {
      e.printStackTrace();
    }

    return stringWriter.toString();
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
