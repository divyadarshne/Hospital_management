package com.hospital.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.service.AppointmentService;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public  class GetAppointmentsByDoctorServlet extends HttpServlet {
    private static final long serialVersionUID  = 5;

    private final AppointmentService service = new AppointmentService();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int doctorId = Integer.parseInt(req.getParameter("doctorId"));

        resp.setContentType("application/json");
        new ObjectMapper().writeValue(resp.getWriter(),
                service.getAppointmentsByDoctor(doctorId)
        );
    }
}