package com.hospital.util;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnClass {
    static DBUtil Dbutil = new DBUtil();

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(Dbutil.getUrl(), Dbutil.getUser(), Dbutil.getPass());
    }
}