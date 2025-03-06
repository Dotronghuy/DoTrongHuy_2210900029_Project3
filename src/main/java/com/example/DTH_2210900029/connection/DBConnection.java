package com.example.DTH_2210900029.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=DTH_2210900029_QLNV;encrypt=false";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "123456";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}

