package com.hospital.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Appointment;
import com.hospital.service.AppointmentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


@WebServlet("/addAppointment")
public class AppointmentServlet  extends HttpServlet{
    private static final Logger APPOINTMENTLOGS = LoggerFactory.getLogger(AppointmentServlet.class);

    private static final long serialVersionUID  = 1;
    private final AppointmentService appointmentService;
    static final ObjectMapper mapper = new ObjectMapper();

    static final String RESPONSETYPE ="application/json";

    public AppointmentServlet(){
        this.appointmentService = new AppointmentService();
    }
    public AppointmentServlet(AppointmentService service) {
        this.appointmentService = service;
    }

    @Override    // add appointment
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws  IOException {

        BufferedReader reader ;
        try {

            reader = req.getReader();
            StringBuilder jsonBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }

            String json1 = jsonBuilder.toString();
            Appointment appointment = mapper.readValue(json1, Appointment.class);

            appointmentService.addAppointment(appointment);

            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("Appointment added successfully");

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(e.getMessage());
            APPOINTMENTLOGS.error(" Error on request ", e);
        }
        APPOINTMENTLOGS.info("Appointment is fixed");
    }
       //update appointment
    public void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        try{

            BufferedReader reader = req.getReader();
            StringBuilder jsonBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }

            String json = jsonBuilder.toString();

            Appointment appointment = mapper.readValue(json, Appointment.class);
            appointmentService.updateAppointment(appointment);
          
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("Successfully updated the appointment");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Appointment update failed");
            resp.getWriter().write(e.getMessage());
            APPOINTMENTLOGS.error(e.getMessage());


        }
    }
          //DeleteAppointmentbyId
    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        try {
            int appointmentId = Integer.parseInt(req.getParameter("appointmentId"));
            appointmentService.deleteAppointment(appointmentId);

            resp.setContentType(RESPONSETYPE);

            resp.getWriter().write("Appointment deleted successfully");
        } catch(Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Appointment delete failed");
        }
    }

}

