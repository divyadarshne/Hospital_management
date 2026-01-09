package com.hospital.servlet;

import com.hospital.service.AdminService;
import com.hospital.util.PasswordHashingUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AdminLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        AdminService adminservice = new AdminService();

        String password = PasswordHashingUtil.hashPassword(req.getParameter("password"));
        String username = req.getParameter("username");

        try {
            adminservice.loginAdmin(username,password);
            // Authentication successful
            resp.getWriter().write("Login successful");

        } catch (IOException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
