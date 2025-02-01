package com.connection.factory;


import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    public static Connection connection = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/adjava", "root", "Manohar@5959");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}

