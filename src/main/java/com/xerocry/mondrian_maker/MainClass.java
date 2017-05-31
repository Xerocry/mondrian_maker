package com.xerocry.mondrian_maker;

import com.xerocry.mondrian_maker.domain.model.entities.Schema;
import com.xerocry.mondrian_maker.domain.model.entities.DatabaseConfiguration;
import org.jdom2.JDOMException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class MainClass {

    private MainClass(String[] args) throws IOException, JDOMException{
        Arguments arguments = new Arguments(args);
        Directories.init(arguments);
        DatabaseConfiguration.init(args);
    }

    private void run() throws IOException{
        String outputFile = (new File("firstVer.xml")).getName();
        PrintWriter outFilePrintWriter = new PrintWriter(new File("outputSchema/" + outputFile));
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Schema.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(DatabaseConfiguration.getSchema(), outFilePrintWriter);
            jaxbMarshaller.marshal(DatabaseConfiguration.getSchema(), System.out);
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
    }

    public static void main(String[] args) {
        try {
            new MainClass(args).run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
