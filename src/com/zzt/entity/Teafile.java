package com.zzt.entity;

import java.io.Serializable;

/**
 * @author 亲爱的子桐
 */
public class Teafile implements Serializable {
    private static final long serialVersionUID = 840554309443377340L;

    private String filename;

    private String filetime;

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setFiletime(String filetime) {
        this.filetime = filetime;
    }

    public String getFiletime() {
        return filetime;
    }

    public String getFilename() {
        return filename;
    }
}
