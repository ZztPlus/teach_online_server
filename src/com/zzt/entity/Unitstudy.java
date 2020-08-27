package com.zzt.entity;

import java.io.Serializable;

public class Unitstudy implements Serializable {
    private static final long serialVersionUID = 840554309443377340L;

    private String unitname;

    private String knowpoint;

    private String summary;

    private String unitid;

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public void setKnowpoint(String knowpoint) {
        this.knowpoint = knowpoint;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUnitname() {
        return unitname;
    }

    public String getKnowpoint() {
        return knowpoint;
    }

    public String getSummary() {
        return summary;
    }

    public void setUnitid(String unitid) {
        this.unitid = unitid;
    }

    public String getUnitid() {
        return unitid;
    }

    @Override
    public String toString() {
        return "Unitstudy{" +
                "unitname='" + unitname + '\'' +
                ", knowpoint='" + knowpoint + '\'' +
                ", summary='" + summary + '\'' +
                ", unitid='" + unitid + '\'' +
                '}';
    }
}
