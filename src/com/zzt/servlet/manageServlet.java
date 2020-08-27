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
@WebServlet(name = "manageServlet", urlPatterns = "/manage")
public class manageServlet extends HttpServlet {
    @Override
    //新建课程
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置UTF-8格式，防止中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/text;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        // 允许跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*");

        PrintWriter writer = response.getWriter();

        // 这是从前端获取的数据

        String gonghao = request.getParameter("pgonghao");
        String coursename = request.getParameter("pcoursename");
        String teachername = request.getParameter("pteachername");

        System.out.println("gonghao:" + gonghao + "coursename" + coursename +" teachername:" + teachername );

        Connection connection = SqlConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into tea_course(gonghao ,course_name,teacher_name) values (?,?,?)");
            preparedStatement.setString(1, gonghao);
            preparedStatement.setString(2, coursename);
            preparedStatement.setString(3, teachername);
            preparedStatement.executeUpdate();  //插入用executeUpdate（）
            writer.print("添加课程成功！");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer.print("添加课程失败！");
    }

    @Override
    //删除课程
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置UTF-8格式，防止中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/text;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        // 允许跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*");

        PrintWriter writer = response.getWriter();

        // 这是从前端获取的数据

        String ppname = request.getParameter("ppname");


        System.out.println(" name:" + ppname );

        Connection connection = SqlConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from tea_course where course_name=? ");
            preparedStatement.setString(1, ppname);
            preparedStatement.executeUpdate();  //插入用executeUpdate（）
            writer.print("课程已删除！");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer.print("课程删除失败！");
    }
}
