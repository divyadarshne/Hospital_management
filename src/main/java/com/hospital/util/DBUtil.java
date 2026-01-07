package com.hospital.util;


public class DBUtil {
    private final  String url = "jdbc:mysql://localhost:3306/hospitalManagement";
    private final  String user = "root";
    private final  String pass = "pass123";

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }


}



