package com.hospital.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.hospital.model.Patient;
import com.hospital.service.PatientService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/patients")
public class PatientServlet extends HttpServlet {

   private PatientService patientService = new PatientService();
    private ObjectMapper mapper = new ObjectMapper();

  @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try{

        BufferedReader reader = req.getReader();
        StringBuilder jsonBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }

            String json = jsonBuilder.toString();

        Patient patient = mapper.readValue(json, Patient.class);
        patientService.addPatient(patient);

        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.getWriter().write("Patient added successfully");

    } catch (Exception e) {
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        resp.getWriter().write(e.getMessage());
    }
    }
    @Override
    protected  void doGet (HttpServletRequest req , HttpServletResponse resp) throws ServletException{
        resp.setStatus(200);
    }
}
