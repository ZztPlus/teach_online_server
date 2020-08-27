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
@WebServlet(name = "adminner_operate_manager_delupdateServlet",urlPatterns = "/adminner_operate_manager_delupdate")
public class adminner_operate_manager_delupdateServlet extends HttpServlet {
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
        String ppassword = request.getParameter("ppw");
        String plevel = request.getParameter("plevel");
        String page = request.getParameter("page");
        String psex = request.getParameter("psex");
        String pphonenumber = request.getParameter("pphonenumber");
        String pgonhao = request.getParameter("pgonhao");

        System.out.println("pname:" + pname + " ppassward:" + ppassword + "plevel:" + plevel + "page" + page + " psex:" + psex + " pphonenumber:" + pphonenumber+ " pgonhao:" + pgonhao);

        Connection connection = SqlConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update manager set name=?,pw=?,level=?,age=?,sex=?,phonenumber=? where gonghao=?");
            preparedStatement.setString(1, pname);
            preparedStatement.setString(2, ppassword);
            preparedStatement.setString(3, plevel);
            preparedStatement.setString(4, page);
            preparedStatement.setString(5, psex);
            preparedStatement.setString(6, pphonenumber);
            preparedStatement.setString(7, pgonhao);
            preparedStatement.executeUpdate();  //插入用executeUpdate（）
            writer.print("教务员信息修改成功，请及时通知!");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer.print("教务员信息修改失败！");
    }

    @Override
    //删除教务员信息
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置UTF-8格式，防止中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/text;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        // 允许跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*");

        PrintWriter writer = response.getWriter();

        // 这是从前端获取的数据

        String pgonghao = request.getParameter("pgonghao");


        System.out.println(" gonghao:" + pgonghao );

        Connection connection = SqlConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from manager where gonghao=? ");
            preparedStatement.setString(1, pgonghao);
            preparedStatement.executeUpdate();  //插入用executeUpdate（）
            writer.print("教务员已删除！");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer.print("教务员教师失败！");
    }
}
