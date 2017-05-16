package com.xerocry.mondrian_maker;

import com.xerocry.mondrian_maker.classes.DatabaseConfiguration;
import org.jdom2.JDOMException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MainClass {

    private MainClass(String[] args) throws IOException, JDOMException{
        Arguments arguments = new Arguments(args);
        Directories.init(arguments);
        DatabaseConfiguration.init(args);
    }

    private void run() throws IOException{
        String outputFile = (new File("firstVer")).getName();
        PrintWriter outFilePrintWriter = new PrintWriter(new File("output" + outputFile));
        try {
            ArrayList<String> scheme = DatabaseConfiguration.getInstance().generateTest();
//            if (scheme.size() == 0) {
//                throw new Exception("Dump has 0 modules");
//            }
            for (String s : scheme) {
                outFilePrintWriter.println(s);
            }
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
