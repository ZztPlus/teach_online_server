package com.zzt.servlet;

import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.zzt.jdbc.SqlConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 亲爱的子桐
 */
@WebServlet(name = "stu_fileServlet",urlPatterns = "/stu_file")
public class stu_fileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/text;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        //这先设置一个文件的存放路径
        String filePath = "D:\\tea_file_path";
        //如果没有该文件夹，就创建一个
        File file = new File(filePath);
        //如果指定路径文件夹不存在就创建一个文件夹
        if (!file.exists()) {
            file.mkdirs();
            //这里的文件对象创建时参数要加上file名
        }
        SmartUpload smart = new SmartUpload();

        smart.initialize(getServletConfig(), request, response);
        //设置上传文件的最大值
        smart.setMaxFileSize(1024 * 1024 * 10);
        //设置上传文件的总最大值
        smart.setTotalMaxFileSize(1024 * 1024 * 100);
        //设置允许上传文件类型
        smart.setAllowedFilesList("jpg,gif,jpeg,png,docx,txt");

        List<String> stufileList = null;
        try {
            //上传
            smart.upload();
            Files files = smart.getFiles();
            stufileList = new LinkedList<>();
            for (int i = 0; i < files.getCount(); i++) {
                com.jspsmart.upload.File temp = files.getFile(i);
                System.out.println(temp.getFileName());
                stufileList.add(temp.getFileName());
                System.out.println("文件名为"+temp.getFileName());
            }

            int n = smart.save(filePath);
            System.out.println("上传了"+n+"个文件");


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("上传失败！");
            System.out.println(e.getMessage());
        }

        //存入数据库

        Connection connection = SqlConnection.getConnection();
        try {
            for (String x : stufileList) {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into stu_file(filename) values (?)");
                preparedStatement.setString(1,x );
                preparedStatement.executeUpdate();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        response.getWriter().print("上传作业成功");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("GBK");
// 新建一个SmartUpload对象
        SmartUpload su = new SmartUpload();
        String name=new String(request.getParameter("name").getBytes("ISO-8859-1"));
// 初始化
        su.initialize(this.getServletConfig(),request,response);
        su.setContentDisposition(null);//如果要实现单击在浏览器打开,注释该即可.
// 下载文件
        try
        {
            System.out.print(name);
            su.downloadFile("D:\\tea_file_path\\"+name);//目录根据实际改变
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
