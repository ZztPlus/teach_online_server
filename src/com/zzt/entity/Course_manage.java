package com.zzt.entity;

import java.io.Serializable;
public class Course_manage implements Serializable{
    private static final long serialVersionUID = 840554309443377340L;

    private String gonghao;
    private String course_name;
    private String course_id;
    private String teacher_name;

    public void setGonghao(String gonghao) {
        this.gonghao = gonghao;
    }

    public String getGonghao() {
        return gonghao;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getTeacher_name() {
        return teacher_name;
    }
}
