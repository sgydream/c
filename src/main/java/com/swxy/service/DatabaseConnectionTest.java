package com.swxy.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/zyue?useSSL=false&&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "123456";

        try {
            // 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // 建立数据库连接
            Connection connection = DriverManager.getConnection(url, username, password);
            
            // 打印连接成功信息
            System.out.println("Database connection successful!");
            
            // 关闭连接
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Database driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error connecting to the database!");
            e.printStackTrace();
        }
    }
}
