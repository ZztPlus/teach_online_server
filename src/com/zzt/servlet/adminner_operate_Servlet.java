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
@WebServlet(name = "adminner_operate_Servlet",urlPatterns = "/adminner_operate")
public class adminner_operate_Servlet extends HttpServlet {
    @Override
    //修改学生信息
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置UTF-8格式，防止中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/text;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        // 允许跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*");

        PrintWriter writer = response.getWriter();

        // 这是从前端获取的数据

        String pname = request.getParameter("pname");
        String ppassword = request.getParameter("ppassword");
        String pcourse = request.getParameter("pcourse");
        String psex = request.getParameter("psex");
        String pphonenumber = request.getParameter("pphonenumber");
        String pxuehao = request.getParameter("pxuehao");

        System.out.println("pname:" + pname + " ppassward:" + ppassword + "pcourse:" + pcourse + " psex:" + psex + " pphonenumber:" + pphonenumber+ " pxuehao:" + pxuehao);

        Connection connection = SqlConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update stu_login set name=?,password=?,course=?,sex=?,phonenumber=? where xuehao=?");
            preparedStatement.setString(1, pname);
            preparedStatement.setString(2, ppassword);
            preparedStatement.setString(3, pcourse);
            preparedStatement.setString(4, psex);
            preparedStatement.setString(5, pphonenumber);
            preparedStatement.setString(6, pxuehao);
            preparedStatement.executeUpdate();  //插入用executeUpdate（）
            writer.print("学生修改成功，请及时通知学生!");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer.print("学生修改失败！");
    }

    @Override
    //查询学生信息
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/text;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        // 允许跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*");

        Connection connection = SqlConnection.getConnection();
        PrintWriter writer = response.getWriter();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from stu_login");
            ResultSet resultSet = statement.executeQuery();
            List<StuLogin> stuLoginList = new LinkedList<>();
            while (resultSet.next()) {
                StuLogin stuLogin = new StuLogin();
                stuLogin.setId(resultSet.getInt("id"));
                stuLogin.setName(resultSet.getString("name"));
                stuLogin.setPassword(resultSet.getString("password"));
                stuLogin.setCourse(resultSet.getString("course"));
                stuLogin.setHomework(Boolean.valueOf(String.valueOf(resultSet.getInt("homework"))));
                stuLogin.setSex(resultSet.getString("sex"));
                stuLogin.setPhonenumber(resultSet.getString("phonenumber"));
                stuLogin.setXuehao(resultSet.getString("xuehao"));
                stuLoginList.add(stuLogin);
            }
            // 把对象转换成json字符串
            writer.print(JSONObject.toJSONString(stuLoginList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
