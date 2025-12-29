package com.hospital.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private final static String URL = "jdbc:mysql://localhost:3306/hospitalManagement";
    private final static String user = "root";
    private final static String pass = "pass123";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, user, pass);
    }

}
