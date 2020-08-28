package com.zzt.servlet;

import com.alibaba.fastjson.JSONObject;
import com.zzt.entity.StuLogin;
import com.zzt.entity.TeaLogin;
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
@WebServlet(name = "teacher_Servlet", urlPatterns = "/teacher_login")
public class teacher_Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/text;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        // 允许跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*");
// TODO: 2020/8/28 这里负责查询教师个人信息
        String number = request.getParameter("number");
        Connection connection = SqlConnection.getConnection();
        PrintWriter writer = response.getWriter();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from tea_login where tea_login.gonghao=?");
            statement.setString(1, number);
            ResultSet resultSet = statement.executeQuery();
            List<TeaLogin> teaLoginList = new LinkedList<>();
            while (resultSet.next()) {
                TeaLogin teaLogin = new TeaLogin();
                teaLogin.setId(resultSet.getInt("id"));
                teaLogin.setName(resultSet.getString("name"));
                teaLogin.setPw(resultSet.getString("pw"));
                teaLogin.setLevel(resultSet.getString("level"));
                teaLogin.setAge(resultSet.getString("age"));
                teaLogin.setSex(resultSet.getString("sex"));
                teaLogin.setPhonenumber(resultSet.getString("phonenumber"));
                teaLogin.setGonghao(resultSet.getString("gonghao"));
                teaLoginList.add(teaLogin);
            }
            writer.print(JSONObject.toJSONString(teaLoginList));  // 把对象转换成json字符串
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
