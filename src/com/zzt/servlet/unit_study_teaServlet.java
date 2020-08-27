package com.zzt.servlet;

import com.alibaba.fastjson.JSONObject;
import com.zzt.entity.Course;
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
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 亲爱的子桐
 */
@WebServlet(name = "unit_study_teaServlet",urlPatterns = "/unit_study_tea")
public class unit_study_teaServlet extends HttpServlet {
    @Override
    //创建单元学习的基本内容
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置UTF-8格式，防止中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/text;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        // 允许跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*");

        PrintWriter writer = response.getWriter();

        // 这是从前端获取的数据

        String unitname = request.getParameter("unitname");
        String knowpoint = request.getParameter("knowpoint");
        String summary = request.getParameter("summary");
        String unitid =  request.getParameter("unitid");

        System.out.println("unitname:" + unitname + " knotpoint:" + knowpoint + "summary:" + summary + "unitid" + unitid);

        Connection connection = SqlConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into unit_study(unitname,knowpoint,summary,unitid) values (?,?,?,?)");
            preparedStatement.setString(1, unitname);
            preparedStatement.setString(2, knowpoint);
            preparedStatement.setString(3, summary);
            preparedStatement.setString(4,unitid);
            preparedStatement.executeUpdate();  //插入用executeUpdate（）
            writer.print("课程单元添加成功!");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer.print("课程单元添加失败!");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/text;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        // 允许跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*");

        Connection connection = SqlConnection.getConnection();
        PrintWriter writer = response.getWriter();
        try {
            PreparedStatement statement = connection.prepareStatement("select unitname,knowpoint,summary,unitid from unit_study");
            ResultSet resultSet = statement.executeQuery();
            List<Unitstudy> unitstudyList = new LinkedList<>();
            while (resultSet.next()) {
                Unitstudy unitstudy = new Unitstudy();
                unitstudy.setUnitname(resultSet.getString("unitname"));
                unitstudy.setKnowpoint(resultSet.getString("knowpoint"));
                unitstudy.setSummary(resultSet.getString("summary"));
                unitstudy.setUnitid(resultSet.getString("unitid"));
                unitstudyList.add(unitstudy);
            }
            System.out.println(unitstudyList);
            writer.print(JSONObject.toJSONString(unitstudyList));  // 把对象转换成json字符串
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
