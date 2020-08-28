package com.zzt.servlet;

import com.alibaba.fastjson.JSONObject;
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
@WebServlet(name = "check_stu_fileServlet",urlPatterns = "/check_stu_file")
public class check_stu_fileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/text;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        // 允许跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*");

        Connection connection = SqlConnection.getConnection();
        PrintWriter writer = response.getWriter();
        try {
            PreparedStatement statement = connection.prepareStatement("select filename,filetime from tea_file");
            ResultSet resultSet = statement.executeQuery();
            List<Teafile> teafileList = new LinkedList<>();
            while (resultSet.next()) {
                Teafile teafile = new Teafile();
                teafile.setFilename(resultSet.getString("filename"));
                //后期用于拓展文件发布功能
                teafile.setFiletime(resultSet.getString("filetime"));
                teafileList.add(teafile);
            }
            // 把对象转换成json字符串
            writer.print(JSONObject.toJSONString(teafileList));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
