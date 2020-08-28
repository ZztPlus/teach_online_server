package com.zzt.entity;

import java.io.Serializable;

public class Stucourse implements Serializable {
    private static final long serialVersionUID = 840554309443377340L;

    private String xuehao;

    private String course_name;

    private String notice;

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public void setXuehao(String xuehao) {
        this.xuehao = xuehao;
    }

    public String getCourse_name() {
        return course_name;
    }

    public String getNotice() {
        return notice;
    }

    public String getXuehao() {
        return xuehao;
    }
}
