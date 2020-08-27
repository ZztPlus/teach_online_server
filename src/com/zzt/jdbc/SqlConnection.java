package com.zzt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlConnection {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/orders?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";

    static final String USER = "root";
    static final String PASS = "123456";

    static Connection connection = null;

    static {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

}
