package com.zzt.servlet;

import com.alibaba.fastjson.JSONObject;
import com.zzt.entity.Course_manage;
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
@WebServlet(name = "manage_selupdateServlet", urlPatterns = "/manage_selupdate")
public class manage_selupdateServlet extends HttpServlet {
    @Override
    //查询课程
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/text;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        // 允许跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*");

        Connection connection = SqlConnection.getConnection();
        PrintWriter writer = response.getWriter();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from tea_course");
            ResultSet resultSet = statement.executeQuery();
            List<Course_manage> course_manage = new LinkedList<>();
            while (resultSet.next()) {
                Course_manage courseManage = new Course_manage();
                courseManage.setGonghao(resultSet.getString("gonghao"));
                courseManage.setCourse_name(resultSet.getString("course_name"));
                courseManage.setCourse_id(resultSet.getString("course_id"));
                courseManage.setTeacher_name(resultSet.getString("teacher_name"));
                course_manage.add(courseManage);
            }
            // 把对象转换成json字符串
            writer.print(JSONObject.toJSONString(course_manage));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    //修改课程
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
        String pcoursename = request.getParameter("pcoursename");
        String pcourseid = request.getParameter("pcourseid");
        String pteachername = request.getParameter("pteachername");

        System.out.println("pgonghao:" + pgonghao + " pcoursename:" + pcoursename + "pcourseid:" + pcourseid + "pteachername" + pteachername);

        Connection connection = SqlConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update tea_course set gonghao=?,course_id=?,teacher_name=? where  course_name=?");
            preparedStatement.setString(1, pgonghao);
            preparedStatement.setString(2, pcourseid);
            preparedStatement.setString(3, pteachername);
            preparedStatement.setString(4, pcoursename);
            preparedStatement.executeUpdate();  //插入用executeUpdate（）
            writer.print("课程信息修改成功，请及时通知!");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer.print("课程信息修改失败！");
    }
}
