package org.pentaho.helloworld;

import java.util.ArrayList;

public class Arguments {
    private static final int TEST_INDEX = 0;
    private static final int OUTPUT_DIR_INDEX = 1;
    private static final int TEMPLATE_DIR_INDEX = 2;

    private static final String OPTION_PREFIX = "-";

    private String[] args;
    private String test;
    private String outputDir;
    private ArrayList<String> options = new ArrayList<>();
    private boolean clean = false;

    public Arguments(String[] args) {
        this.args = args;
        readSerial();
    }

    private void readSerial() {
        test = args[TEST_INDEX];
        outputDir = args[OUTPUT_DIR_INDEX];

        addOptions(TEMPLATE_DIR_INDEX + 1);
    }

    private boolean addOptions(int index) {
        if (index >= args.length) {
            return true;
        }

        for (int i = index; i < args.length; i++) {
            if (!args[i].startsWith(OPTION_PREFIX)) {
                throw new IllegalArgumentException("Unknown argument '" + args[i] + "'");
            }

            options.add(args[i]);
        }
        return true;
    }

    public String getTest() {
        return test;
    }

    String getOutputDir() {
        return outputDir;
    }

    public ArrayList<String> getOptions() {
        return options;
    }
}
