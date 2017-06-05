package org.xerocry.mondrian;

import java.io.File;
import java.io.FileNotFoundException;


public class Directories {
    private static final String XML_EXTENSION = ".xml";
    private static final String DIRECTORY_SEPARATOR = "/";

    private static final String WORD_TEMPLATE = "\\w+";
    private static final String NOT_WORD_SYMBOL = "\\W";
    private static final String UNDERSCORE = "_";

    public final String testXMLPath;
    private final String outPath;

    private static Directories instance;

    private Directories(Arguments args) throws FileNotFoundException {
        testXMLPath = addXmlExtension(args.getTest());
        outPath = addDirectoryExtension(args.getOutputDir());
    }

    public static void init(Arguments args) throws FileNotFoundException {
        if (instance == null) {
            instance = new Directories(args);
        }
    }

    public static Directories getInstance() {
        return instance;
    }

    public static void deleteInstance() {
        instance = null;
    }

    private void checkDirectoryExists(String directory) throws FileNotFoundException {
        if (new File(directory).isDirectory()) {
            return;
        }

        throw new FileNotFoundException("Directory : " + directory + " not found\n Enter an existing Directory ");
    }

    private void checkFileExist(String file) throws FileNotFoundException {
        if ((new File(file)).isFile()) {
            return;
        }

        throw new FileNotFoundException("File: " + file + " not found \n" + "Enter the required .xml");
    }

    private String addDirectoryExtension(String directory) throws FileNotFoundException {
        checkDirectoryExists(directory);

        if (!directory.endsWith(DIRECTORY_SEPARATOR)) {
            return directory + DIRECTORY_SEPARATOR;
        }

        return directory;
    }

    private String addXmlExtension(String file) throws FileNotFoundException {
        checkFileExist(file);
        return file;
    }
}
