package com.hospital.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Doctor;

import com.hospital.service.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


public class DoctorServlet extends HttpServlet {
    private static final Logger DOCTORSERVLETLOGS = LoggerFactory.getLogger(DoctorServlet.class);
    private final DoctorService doctorService;
    private static final long serialVersionUID  = 6;

    public DoctorServlet() {   //default constructor for nrml build
        this.doctorService = new DoctorService();
    }

    public DoctorServlet(DoctorService doctorService) {   //for testing we need argument constructor
        this.doctorService = doctorService;
    }
    private final ObjectMapper mapper = new ObjectMapper();
    static final String RESPONSETYPE ="application/json";
   
    //Add Doctor
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)  throws  IOException {
        try {

            BufferedReader reader = req.getReader();
            StringBuilder jsonBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            String json = jsonBuilder.toString();

            Doctor doctor = mapper.readValue(json, Doctor.class);
            doctorService.addDoctor(doctor);

            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("Doctor added successfully");
            DOCTORSERVLETLOGS.info("Doctor added successfully");


        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(e.getMessage());
            DOCTORSERVLETLOGS.error(e.getMessage());
        }
        DOCTORSERVLETLOGS.info("DoctorServlet runs successfully");
    }

  //get doctor by Id
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int pId = Integer.parseInt(req.getParameter("doctorid"));

        resp.setContentType(RESPONSETYPE);
        new ObjectMapper().writeValue(resp.getWriter(), doctorService.getDoctorById(pId));
        DOCTORSERVLETLOGS.info("Fetched doctor {}", pId);

    }

}

