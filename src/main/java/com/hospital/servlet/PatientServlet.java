package com.hospital.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.hospital.model.Patient;
import com.hospital.service.DoctorService;
import com.hospital.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import javax.servlet.ServletException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;


@WebServlet("/patients")
public class PatientServlet extends HttpServlet {
    private static final Logger patientServletLogs = LoggerFactory.getLogger(PatientServlet.class);
    private final PatientService patientService = new PatientService();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override                   //add patients
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  throws  IOException {
        try {

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
            patientServletLogs.error(e.getMessage());
        }
        patientServletLogs.info("PatientServlet runs successfully");
    }

    @Override                 //update patient
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Patient patient = mapper.readValue(req.getInputStream(), Patient.class);
        Patient updated = patientService.updatePatient(patient);

        resp.setContentType("application/json");
        try{
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("Patient updated successfully");

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(e.getMessage());
            patientServletLogs.error(e.getMessage());
        }
        patientServletLogs.info("UpdatePatientServlet runs successfully");
    }
                     //get patient by ID
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int pId = Integer.parseInt(req.getParameter("patientid"));

        resp.setContentType("application/json");
        new ObjectMapper().writeValue(resp.getWriter(), patientService.getPatientById(pId));
    }
}
