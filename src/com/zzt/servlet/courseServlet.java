package com.zzt.servlet;

import com.alibaba.fastjson.JSONObject;
import com.zzt.entity.TeaLogin;
import com.zzt.jdbc.SqlConnection;
import com.zzt.entity.Course;

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
@WebServlet(name = "courseServlet",urlPatterns = "/course")
public class courseServlet extends HttpServlet {
    @Override
    //老师发布公告用这个
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 设置UTF-8格式，防止中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/text;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        // 允许跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*");

        PrintWriter writer = response.getWriter();

        // 这是从前端获取的数据

        String name = request.getParameter("username");
        String teacherid = request.getParameter("pteacherid");
        String notice = request.getParameter("pnotice");

        System.out.println("name:" + name + " teacherid:" + teacherid + " notice:" + notice);

        Connection connection = SqlConnection.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into course(name ,teacherid,notice) values (?,?,?)");
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, teacherid);
                preparedStatement.setString(3, notice);
                preparedStatement.executeUpdate();  //插入用executeUpdate（）
                writer.print("发布成功！");
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        writer.print("发布失败！");
    }

    // TODO: 2020/8/26 建立前端的课程框，直接用这里的信息
    @Override
    //学生获得公告用这个
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/text;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        // 允许跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*");

        Connection connection = SqlConnection.getConnection();
        PrintWriter writer = response.getWriter();
        try {
            PreparedStatement statement = connection.prepareStatement("select name,notice from course");
            ResultSet resultSet = statement.executeQuery();
            List<Course> courseList = new LinkedList<>();
            while (resultSet.next()) {
                Course course = new Course();
                course.setName(resultSet.getString("name"));
                course.setNotice(resultSet.getString("notice"));
                courseList.add(course);
                System.out.println(course.getName() + course.getNotice());
            }
            // 把对象转换成json字符串
            writer.print(JSONObject.toJSONString(courseList));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
