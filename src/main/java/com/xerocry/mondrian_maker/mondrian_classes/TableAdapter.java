package com.xerocry.mondrian_maker.mondrian_classes;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class TableAdapter extends XmlAdapter<TableAdapter.AdaptedTable, String> {

    @Override
    public String unmarshal(AdaptedTable v) throws Exception {
        return v.name;
    }

    @Override
    public AdaptedTable marshal(String v) throws Exception {
        AdaptedTable amf = new AdaptedTable();
        if (v == null) {
            return null;
        }
        amf.name = v;
        return amf;
    }

    public static class AdaptedTable {

        @XmlAttribute
        public String name;

    }

}