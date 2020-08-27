package com.zzt.entity;

import java.io.Serializable;

/**
 * (StuLogin)实体类
 *
 * @author makejava
 * @since 2020-08-06 23:14:33
 */
public class StuLogin implements Serializable {
    private static final long serialVersionUID = 840554309443377340L;

    private Integer id;

    private String name;

    private String password;
    /**
     * 布尔类型，是否提交了作业
     */
    private Boolean homework;

    private String course;

    private String sex;

    private String phonenumber;

    private String xuehao;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getHomework() {
        return homework;
    }

    public void setHomework(Boolean homework) {
        this.homework = homework;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getXuehao() { return xuehao; }

    public void setXuehao(String xuehao) {
        this.xuehao = xuehao;
    }

}