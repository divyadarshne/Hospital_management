package com.hospital.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Doctor;

import com.hospital.service.DoctorService;
import com.hospital.service.DoctorService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/doctors")
public class DoctorServlet extends HttpServlet {
    private static DoctorService doctorService = new DoctorService();
    private final ObjectMapper mapper = new ObjectMapper();
    String responseType ="application/json";
    private static final long serialVersionUID  = 2;

    public DoctorServlet() {   //default constructor for nrml build
        this.doctorService = new DoctorService();
    }

    public DoctorServlet(DoctorService doctorService) {   //for testing we need argument constructor
        this.doctorService = doctorService;
    }

    //Add Doctor
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws  IOException {
        try{

            BufferedReader reader = req.getReader();
            StringBuilder jsonBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }

            String json = jsonBuilder.toString();

            Doctor doc = mapper.readValue(json, Doctor.class);
            doctorService.addDoctor(doc);

            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("Doctor added successfully");

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(e.getMessage());
        }
    }

  //get doctor by Id
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        resp.setContentType(responseType);
        new ObjectMapper().writeValue(resp.getWriter(), doctorService.getDoctorById(id));
    }

}

