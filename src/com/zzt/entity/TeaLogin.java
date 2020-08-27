package com.zzt.entity;

import java.io.Serializable;

public class TeaLogin implements Serializable{
    private static final long serialVersionUID = 840554309443377340L;

    private Integer id;

    private String name;

    private String pw;
    /**
     * 布尔类型，是否提交了作业
     */
    private String level;

    private String age;

    private String sex;

    private String phonenumber;

    private String gonghao;


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

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getLevel() { return level; }

    public void setLevel(String level) { this.level = level; }

    public String getAge() { return age; }

    public void setAge(String age) { this.age = age; }

    public String getSex() { return sex; }

    public void setSex(String sex) { this.sex = sex; }

    public String getPhonenumber() { return phonenumber; }

    public void setPhonenumber(String phonenumber) { this.phonenumber = phonenumber; }

    public String getGonghao() { return gonghao; }

    public void setGonghao(String gonghao) { this.gonghao = gonghao; }
}

