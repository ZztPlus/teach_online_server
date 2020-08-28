package com.zzt.servlet;

import com.alibaba.fastjson.JSONObject;
import com.zzt.entity.Stucourse;
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
// TODO: 2020/8/27 course post
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

    // TODO: 2020/8/26 course get
    @Override
    //学生获得公告用这个
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/text;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        // 允许跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*");

        String number = request.getParameter("number");

        Connection connection = SqlConnection.getConnection();
        PrintWriter writer = response.getWriter();
        // TODO: 2020/8/27 这里是从数据库中查数据，循环创建学生课程方块 notice 无
        try {
            PreparedStatement statement = connection.prepareStatement("select course_name,notice from stu_course where xuehao=?");
            statement.setString(1, number);
            ResultSet resultSet = statement.executeQuery();
            List<Stucourse> courseList = new LinkedList<>();
            while (resultSet.next()) {
                Stucourse stucourse = new Stucourse();
                stucourse.setCourse_name(resultSet.getString("course_name"));
                stucourse.setNotice(resultSet.getString("notice"));
                courseList.add(stucourse);
                System.out.println(stucourse.getCourse_name() + stucourse.getNotice());
            }
            // 把对象转换成json字符串
            writer.print(JSONObject.toJSONString(courseList));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
