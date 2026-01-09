package com.hospital.servlet;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Admin;
import com.hospital.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AdminRegisterServlet extends HttpServlet {
    private final ObjectMapper mapper = new ObjectMapper();
    AdminService adminservice = new AdminService();
    Logger adminRegisterLogging = LoggerFactory.getLogger(AdminRegisterServlet.class);
     String  success = "Admin Registered Successfully";

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      try{
           Admin admin = mapper.readValue(req.getInputStream(), Admin.class);
        if (admin.getUserName() == null || admin.getPassword() == null || admin.getEmail() == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing fields");
        }
            adminservice.registeringAdmin(admin);
            resp.getWriter().write(success);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            adminRegisterLogging.info(success);

      } catch (IOException e) {
          resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "User already exists");
          adminRegisterLogging.error("Error registering admin");
      }

    }
}
