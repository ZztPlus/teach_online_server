package com.zzt.servlet;

import com.alibaba.fastjson.JSONObject;
import com.zzt.entity.StuLogin;
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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
//文件上传下载，单元学习，课程信息
/**
 * @author 亲爱的子桐
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置UTF-8格式，防止中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/text;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        // 允许跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*");

        PrintWriter writer = response.getWriter();

        // 这是从前端获取的数据
        String number = request.getParameter("username");
        String psd = request.getParameter("psd");
        String user = request.getParameter("user");

        System.out.println("number:" + number + " password:" + psd + " user:" + user);

        Connection connection = SqlConnection.getConnection();
        if ("学生".equals(user)) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("select * from stu_login where xuehao=?");
                preparedStatement.setString(1, number);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.getString("password").equals(psd)) {
                        writer.print("登陆成功！");
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if ("老师".equals(user)){
            try {                      // TODO: 2020/8/28 这里缺少sql语句
                PreparedStatement preparedStatement = connection.prepareStatement("select * from tea_login where gonghao=?           ");
                preparedStatement.setString(1, number);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.getString("pw").equals(psd)) {
                        writer.print("登陆成功！");
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            writer.print("登陆失败！用户名或密码错误");
        } else if ("管理员".equals(user)){
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("select * from  adminner where gonghao=?");
                preparedStatement.setString(1, number);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.getString("password").equals(psd)) {
                        writer.print("登陆成功！");
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        else if ("教务员".equals(user)){
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("select * from manager where gonghao=?");
                preparedStatement.setString(1, number);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.getString("pw").equals(psd)) {
                        writer.print("登陆成功！");
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            writer.print("登陆失败！用户名或密码错误");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/text;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        // 允许跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*");

        String xuehao = request.getParameter("number");

        Connection connection = SqlConnection.getConnection();
        PrintWriter writer = response.getWriter();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from stu_login where stu_login.xuehao=?");
            statement.setString(1, xuehao);
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
            writer.print(JSONObject.toJSONString(stuLoginList));  // 把对象转换成json字符串
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
