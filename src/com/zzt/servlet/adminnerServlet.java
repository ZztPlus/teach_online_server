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
@WebServlet(name = "adminnerServlet",urlPatterns = "/adminner")
public class adminnerServlet extends HttpServlet {
    //系统管理员用于增学生
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

        String pname = request.getParameter("pname");
        String ppassword = request.getParameter("ppassword");
        String pcourse = request.getParameter("pcourse");
        String psex = request.getParameter("psex");
        String pphonenumber = request.getParameter("pphonenumber");
        String pxuehao = request.getParameter("pxuehao");

        System.out.println("pname:" + pname + " ppassward:" + ppassword + "pcourse:" + pcourse + " psex:" + psex + " pphonenumber:" + pphonenumber+ " pxuehao:" + pxuehao);

        Connection connection = SqlConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into stu_login(name,password,course,sex,phonenumber,xuehao) values (?,?,?,?,?,?)");
            preparedStatement.setString(1, pname);
            preparedStatement.setString(2, ppassword);
            preparedStatement.setString(3, pcourse);
            preparedStatement.setString(4, psex);
            preparedStatement.setString(5, pphonenumber);
            preparedStatement.setString(6, pxuehao);
            preparedStatement.executeUpdate();  //插入用executeUpdate（）
            writer.print("新学生注册成功，请及时通知学生!");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer.print("学生注册失败！");
    }

    @Override
    //管理员删除学生
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置UTF-8格式，防止中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/text;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        // 允许跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*");

        PrintWriter writer = response.getWriter();

        // 这是从前端获取的数据

        String xuehao = request.getParameter("ppxuehao");


        System.out.println(" xuehao:" + xuehao );

        Connection connection = SqlConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from stu_login where xuehao=? ");
            preparedStatement.setString(1, xuehao);
            preparedStatement.executeUpdate();  //插入用executeUpdate（）
            writer.print("学生已删除！");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer.print("删除学生失败！");
    }
}
