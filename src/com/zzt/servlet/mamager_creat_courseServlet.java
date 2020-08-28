package com.zzt.servlet;

import com.alibaba.fastjson.JSONObject;
import com.zzt.entity.Stucourse;
import com.zzt.jdbc.SqlConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 亲爱的子桐
 */
@WebServlet(name = "mamager_creat_courseServlet",urlPatterns = "/mamager_creat_course")
public class mamager_creat_courseServlet extends HttpServlet {

    // TODO: 2020/8/28 查询课程公告的方法接用了这里的post方法
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
