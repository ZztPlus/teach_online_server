package com.zzt.servlet;

import com.alibaba.fastjson.JSONObject;
import com.zzt.entity.Online_homework;
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
 * 该部分名字与单元学习部分略有相似。但是该部分内容并不是单元学习。
 * 而是学生提交作业，老师查询学生的作业（非文件版）
 */
@WebServlet(name = "unit_online_studyServlet",urlPatterns = "/unit_online_studyServlet")
public class unit_online_studyServlet extends HttpServlet {
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

        String stuname = request.getParameter("stuname");
        String stuxuehao = request.getParameter("stuxuehao");
        String homework = request.getParameter("homework");

        System.out.println("stuname:" + stuname + "stuxuehao:" + stuxuehao + "homework:" + homework );

        Connection connection = SqlConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into online_homework(stuname,stuxuehao,homework) values (?,?,?)");
            preparedStatement.setString(1, stuname);
            preparedStatement.setString(2, stuxuehao);
            preparedStatement.setString(3, homework);
            preparedStatement.executeUpdate();  //插入用executeUpdate（）
            writer.print("作业提交成功，等待老师批阅!");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer.print("作业提交失败！");
    }

    @Override
    //老师查询学生在线作业
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/text;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        // 允许跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*");

        Connection connection = SqlConnection.getConnection();
        PrintWriter writer = response.getWriter();
        String stuxuehao = request.getParameter("stuxuehao");
        System.out.println("stuxuehao:" + stuxuehao);
        try {
            PreparedStatement statement = connection.prepareStatement("select * from online_homework where stuxuehao=?");
            statement.setString(1, stuxuehao);
            ResultSet resultSet = statement.executeQuery();
            List<Online_homework> online_homeworkList = new LinkedList<>();
            while (resultSet.next()) {
                Online_homework onlineHomework = new Online_homework();
                onlineHomework.setStuname(resultSet.getString("stuname"));
                onlineHomework.setStuxuehao(resultSet.getString("stuxuehao"));
                onlineHomework.setHomework(resultSet.getString("homework"));
                online_homeworkList.add(onlineHomework);
            }
            // 把对象转换成json字符串
            writer.print(JSONObject.toJSONString(online_homeworkList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
