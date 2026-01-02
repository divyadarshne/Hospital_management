package com.hospital.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.hospital.model.Patient;
import com.hospital.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;



@WebServlet("/patients")
public class PatientServlet extends HttpServlet {
    private static final Logger PATIENTSERVLETLOGS = LoggerFactory.getLogger(PatientServlet.class);
    private final PatientService patientService;
    private static final long serialVersionUID  = 6;

    public PatientServlet() {   //default constructor for nrml build
        this.patientService = new PatientService();
    }

    public PatientServlet(PatientService patientService) {   //for testing we need argument constructor
        this.patientService = patientService;
    }
    private final ObjectMapper mapper = new ObjectMapper();
    static final String RESPONSETYPE ="application/json";

    @Override                   //add patients
    public void doPost(HttpServletRequest req, HttpServletResponse resp)  throws  IOException {
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
            PATIENTSERVLETLOGS.info("Patient added successfully");


        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(e.getMessage());
            PATIENTSERVLETLOGS.error(e.getMessage());
        }
        PATIENTSERVLETLOGS.info("PatientServlet runs successfully");
    }

    @Override                 //update patient
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Patient patient = mapper.readValue(req.getInputStream(), Patient.class);
        patientService.updatePatient(patient);

        resp.setContentType(RESPONSETYPE);
        try{
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("Patient updated successfully");

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(e.getMessage());
            PATIENTSERVLETLOGS.error(e.getMessage());
        }
        PATIENTSERVLETLOGS.info("UpdatePatientServlet runs successfully");
    }
                     //get patient by ID
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int pId = Integer.parseInt(req.getParameter("patientid"));

        resp.setContentType(RESPONSETYPE);
        new ObjectMapper().writeValue(resp.getWriter(), patientService.getPatientById(pId));
        PATIENTSERVLETLOGS.info("Fetched patient {}", pId);

    }
}
