package com.hospital.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.service.PatientService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getpatientbyid")
public class GetPatientByIdServlet extends HttpServlet {

    private PatientService patientservice = new PatientService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int pId = Integer.parseInt(req.getParameter("patientid"));

        resp.setContentType("application/json");
        new ObjectMapper().writeValue(resp.getWriter(), patientservice.getPatientById(pId));
    }
}