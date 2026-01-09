package com.hospital.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Patient;
import com.hospital.service.PatientService;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class GetAllPatientServlet extends HttpServlet {
    private static final long serialVersionUID  = 5;

    private final PatientService service = new PatientService();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Patient> patients = service.getAllPatients();

        resp.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(), patients);
    }
}