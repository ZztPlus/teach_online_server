package com.zzt.entity;

import java.io.Serializable;

public class Course implements Serializable{
    private static final long serialVersionUID = 840554309443377340L;

    private Integer id;
    private String name;
    private String teacherid;
    private String studentid;
    private String notice;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid;
    }

    public String getTeacherid() {
        return teacherid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getNotice() {
        return notice;
    }
}
