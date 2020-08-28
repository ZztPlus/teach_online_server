package com.zzt.servlet;

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

/**
 * @author 亲爱的子桐
 */
@WebServlet(name = "add_stu_to_courseServlet",urlPatterns = "/add_stu_to_course")
public class add_stu_to_courseServlet extends HttpServlet {


    // TODO: 2020/8/27 老师添加学生功能，数据用来确定哪位同学的课表用来显示哪些课
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

        String xuehao = request.getParameter("xuehao");
        String course_name = request.getParameter("course_name");
        

        System.out.println("xuehao:" + xuehao + " course_name:" + course_name );

        Connection connection = SqlConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into stu_course(xuehao,course_name) values (?,?)");
            preparedStatement.setString(1, xuehao);
            preparedStatement.setString(2, course_name);
            preparedStatement.executeUpdate();  //插入用executeUpdate（）
            writer.print("添加学生成功，请及时发布课程通知！");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer.print("添加学生失败！");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
