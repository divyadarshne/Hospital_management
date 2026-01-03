package com.hospital.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.service.AppointmentService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getallappointments")
public class GetAllAppointmentServlet extends HttpServlet {
    private static final long serialVersionUID  = 3;

    static  AppointmentService services = new AppointmentService();
    static ObjectMapper mapper = new ObjectMapper();   // to map object into json

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        mapper.writeValue(resp.getWriter(), services.getAllAppointments());
    }
}