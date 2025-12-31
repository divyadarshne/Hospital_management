package com.hospital.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Doctor;

import com.hospital.service.DoctorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/doctors")
public class DoctorServlet extends HttpServlet {
    private DoctorService doctorService = new DoctorService();
    private ObjectMapper mapper = new ObjectMapper();
   //Add Doctor
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

        resp.setContentType("application/json");
        new ObjectMapper().writeValue(resp.getWriter(), doctorService.getDoctorById(id));
    }

}

