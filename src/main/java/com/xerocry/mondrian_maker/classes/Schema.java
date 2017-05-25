package com.xerocry.mondrian_maker.classes;

import com.xerocry.mondrian_maker.mondrian_classes.Dimension;

import java.util.ArrayList;
import java.util.HashMap;

public class DbScheme {

    private HashMap<String, Dimension> dimensions = new HashMap<>();

    public enum Hierarchy {

        OS(null),
        Windows(OS),
        WindowsNT(Windows),
        WindowsNTWorkstation(WindowsNT),
        WindowsNTServer(WindowsNT),
        Windows2000(Windows),
        Windows2000Server(Windows2000),
        Windows2000Workstation(Windows2000),
        WindowsXp(Windows),
        WindowsVista(Windows),
        Windows7(Windows),
        Windows95(Windows),
        Windows98(Windows),
        Unix(OS) {
            @Override
            public boolean supportsXWindows() {
                return true;
            }
        },
        Linux(Unix),
        AIX(Unix),
        HpUx(Unix),
        SunOs(Unix),
        ;
        private Hierarchy parent = null;

        private Hierarchy(Hierarchy parent) {
            this.parent = parent;
        }
    }

    public ArrayList<String> generateScheme() {
        return null;
    }

}
