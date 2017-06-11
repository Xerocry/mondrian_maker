package org.xerocry.mondrian.endpoints;

import org.jdom2.JDOMException;
import org.xerocry.mondrian.DatabaseConfiguration;
import org.xerocry.mondrian.Schema;
import org.xerocry.mondrian.endpoints.dtos.responses.StringOperationResultDTO;
import org.xml.sax.SAXException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringWriter;

@Path("@plugin.java.rest.path.root@")
public class PentahoMondrianService {
    public PentahoMondrianService() { }

    @GET
    @Path("/hello")
    @Produces(MediaType.APPLICATION_JSON)
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
    @Path("/mondrian")
    @Produces(MediaType.APPLICATION_XML)
    public String mondrian(@QueryParam("host") String host,
                           @QueryParam("user") String user,
                           @QueryParam("pass") String pass)
            throws ParserConfigurationException, SAXException, IOException, JDOMException {

        String url = "http://" + host.trim() + "?" + user.trim() + "?" + pass.trim();
        DatabaseConfiguration.init(url);

        Schema schema = DatabaseConfiguration.getSchema();

        StringWriter stringWriter = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Schema.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(schema, System.out);
            jaxbMarshaller.marshal(schema, stringWriter);
            if (schema.getCubes().size() == 0) {
                throw new Exception("Dump has 0 modules");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stringWriter.toString();
    }
}
