package com.xerocry.mondrian_maker.domain.model.factories;

import com.xerocry.mondrian_maker.Arguments;
import com.xerocry.mondrian_maker.Directories;
import com.xerocry.mondrian_maker.domain.model.entities.DatabaseConfiguration;
import com.xerocry.mondrian_maker.domain.model.entities.Schema;
import com.xerocry.mondrian_maker.domain.model.entities.User;
import com.xerocry.mondrian_maker.domain.model.entities.interfaces.IAddress;
import com.xerocry.mondrian_maker.domain.model.entities.interfaces.IUser;
import com.xerocry.mondrian_maker.domain.model.factories.interfaces.IAddressFactory;
import com.xerocry.mondrian_maker.domain.model.factories.interfaces.ISchemaFactory;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;

/**
 * Created by raskia on 5/31/2017.
 */
public class ShemaFactory implements ISchemaFactory {

    @Autowired
    public ShemaFactory(String[] args) throws IOException, JDOMException {
        Arguments arguments = new Arguments(args);
        Directories.init(arguments);
        DatabaseConfiguration.init(args);
    }
    @Override
    public String validSchema() throws FileNotFoundException {

        String outputFile = (new File("firstVer.xml")).getName();
        PrintWriter outFilePrintWriter = new PrintWriter(new File("outputSchema/" + outputFile));
        StringWriter stringWriter = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Schema.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(DatabaseConfiguration.getSchema(), outFilePrintWriter);
            jaxbMarshaller.marshal(DatabaseConfiguration.getSchema(), System.out);
            jaxbMarshaller.marshal(DatabaseConfiguration.getSchema(), stringWriter);
//            if (scheme.size() == 0) {
//                throw new Exception("Dump has 0 modules");
//            }
//            for (String s : DatabaseConfiguration.getInstance()) {
//                outFilePrintWriter.println(s);
//            }

        } catch (JAXBException e) {
            e.printStackTrace();
        } finally {
            Directories.deleteInstance();
        }
        return stringWriter.toString();
    }
}
