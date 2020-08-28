package com.zzt.servlet;

import com.alibaba.fastjson.JSONObject;
import com.zzt.entity.Course;
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
@WebServlet(name = "course_manageServlet",urlPatterns = "/course_manage")
public class course_manageServlet extends HttpServlet {
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

        String gonghao = request.getParameter("gonghao");
        String course_name = request.getParameter("course_name");
        String teacher_name = request.getParameter("teacher_name");
        String xuehao = request.getParameter("xuehao");
        String number = request.getParameter("number");

        System.out.println("gonghao:" + gonghao + " course_name:" + course_name + "teacher_name:" + teacher_name + " xuehao:" + xuehao);

        Connection connection = SqlConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into tea_course(gonghao ,course_name,teacher_name,xuehao) values (?,?,?,?)");
            preparedStatement.setString(1, gonghao);
            preparedStatement.setString(2, course_name);
            preparedStatement.setString(3, teacher_name);
            preparedStatement.setString(4, xuehao);
            preparedStatement.executeUpdate();  //插入用executeUpdate（）
            writer.print("操作成功！");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer.print("添加学生失败！");
    }

    @Override
    //老师查询自己的课程任务
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/text;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        // 允许跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*");

        String number = request.getParameter("number");

        Connection connection = SqlConnection.getConnection();
        PrintWriter writer = response.getWriter();
        try {
            PreparedStatement statement = connection.prepareStatement("select name,id from course where teacherid=?");
            statement.setString(1, number);
            ResultSet resultSet = statement.executeQuery();
            List<Course> courseList = new LinkedList<>();
            while (resultSet.next()) {
                Course course = new Course();
                course.setName(resultSet.getString("name"));
                course.setId(Integer.valueOf(resultSet.getString("id")));
                courseList.add(course);
            }
            writer.print(JSONObject.toJSONString(courseList));  // 把对象转换成json字符串
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
