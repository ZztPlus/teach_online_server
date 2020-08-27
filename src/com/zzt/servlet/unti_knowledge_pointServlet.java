package com.zzt.servlet;

import com.alibaba.fastjson.JSONObject;
import com.zzt.entity.Online_homework;
import com.zzt.entity.TeaLogin;
import com.zzt.entity.Unitstudy;
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
import java.util.LinkedList;
import java.util.List;

/**
 * @author 亲爱的子桐
 */
@WebServlet(name = "unti_knowledge_pointServlet",urlPatterns = "/unti_knowledge_point")
public class unti_knowledge_pointServlet extends HttpServlet {
    @Override
    //获取老师发布的知识点
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/text;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        // 允许跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*");

        Connection connection = SqlConnection.getConnection();
        PrintWriter writer = response.getWriter();
        String unitname = request.getParameter("unitname");
        System.out.println("unitname:" + unitname);
        try {
            PreparedStatement statement = connection.prepareStatement("select knowpoint,unitname from unit_study where unitname=?");
            statement.setString(1, unitname);
            ResultSet resultSet = statement.executeQuery();
            List<Unitstudy> unitstudyList = new LinkedList<>();
            while (resultSet.next()) {
                Unitstudy unitstudy = new Unitstudy();
                unitstudy.setKnowpoint(resultSet.getString("knowpoint"));
                unitstudy.setUnitname(resultSet.getString("unitname"));
                unitstudyList.add(unitstudy);
            }
            // 把对象转换成json字符串
            writer.print(JSONObject.toJSONString(unitstudyList));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
