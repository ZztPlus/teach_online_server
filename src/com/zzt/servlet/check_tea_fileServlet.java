package com.zzt.servlet;

import com.alibaba.fastjson.JSONObject;
import com.zzt.entity.Course;
import com.zzt.entity.Stufile;
import com.zzt.entity.Teafile;
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
@WebServlet(name = "check_tea_fileServlet",urlPatterns = "/check_tea_file")
public class check_tea_fileServlet extends HttpServlet {
    @Override
    //查询已经上传的文件
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/text;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        // 允许跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*");

        Connection connection = SqlConnection.getConnection();
        PrintWriter writer = response.getWriter();
        try {
            PreparedStatement statement = connection.prepareStatement("select filename,filetime from stu_file");
            ResultSet resultSet = statement.executeQuery();
            List<Stufile> stufileList = new LinkedList<>();
            while (resultSet.next()) {
                Stufile stufile = new Stufile();
                stufile.setFilename(resultSet.getString("filename"));
                //后期用于拓展文件发布功能
                stufile.setFiletime(resultSet.getString("filetime"));
                stufileList.add(stufile);
            }
            writer.print(JSONObject.toJSONString(stufileList));  // 把对象转换成json字符串
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
