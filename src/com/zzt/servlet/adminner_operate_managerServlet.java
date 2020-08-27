package com.zzt.servlet;

import com.alibaba.fastjson.JSONObject;
import com.zzt.entity.ManagerLogin;
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
@WebServlet(name = "adminner_operate_managerServlet",urlPatterns = "/adminner_operate_manager")
public class adminner_operate_managerServlet extends HttpServlet {
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
            PreparedStatement preparedStatement = connection.prepareStatement("insert into manager(name,pw,level,age,sex,phonenumber,gonghao) values (?,?,?,?,?,?,?)");
            preparedStatement.setString(1, pname);
            preparedStatement.setString(2, ppassword);
            preparedStatement.setString(3, plevel);
            preparedStatement.setString(4, page);
            preparedStatement.setString(5, psex);
            preparedStatement.setString(6, pphonenumber);
            preparedStatement.setString(7, pgonhao);
            preparedStatement.executeUpdate();  //插入用executeUpdate（）
            writer.print("新教务员添加成功，请及时通知!");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer.print("教务员添加失败！");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/text;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        // 允许跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*");

        Connection connection = SqlConnection.getConnection();
        PrintWriter writer = response.getWriter();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from manager");
            ResultSet resultSet = statement.executeQuery();
            List<ManagerLogin> teaLoginList = new LinkedList<>();
            while (resultSet.next()) {
                ManagerLogin manageLogin = new ManagerLogin();
                manageLogin.setId(resultSet.getInt("id"));
                manageLogin.setName(resultSet.getString("name"));
                manageLogin.setPw(resultSet.getString("pw"));
                manageLogin.setLevel(resultSet.getString("level"));
                manageLogin.setAge(resultSet.getString("age"));
                manageLogin.setSex(resultSet.getString("sex"));
                manageLogin.setPhonenumber(resultSet.getString("phonenumber"));
                manageLogin.setGonghao(resultSet.getString("gonghao"));
                teaLoginList.add(manageLogin);
            }
            // 把对象转换成json字符串
            writer.print(JSONObject.toJSONString(teaLoginList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
