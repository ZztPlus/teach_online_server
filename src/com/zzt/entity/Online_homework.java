package com.zzt.entity;

import java.io.Serializable;

/**
 * @author 亲爱的子桐
 */
public class Online_homework implements Serializable {
    private static final long serialVersionUID = 840554309443377340L;

    private String stuname;

    private String stuxuehao;

    private String homework;

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public void setStuxuehao(String stuxuehao) {
        this.stuxuehao = stuxuehao;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    public String getStuname() {
        return stuname;
    }

    public String getStuxuehao() {
        return stuxuehao;
    }

    public String getHomework() {
        return homework;
    }
}
