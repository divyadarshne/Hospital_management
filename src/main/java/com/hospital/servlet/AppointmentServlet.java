package com.hospital.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Appointment;
import com.hospital.service.AppointmentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


@WebServlet("/addAppointment")
public class AppointmentServlet  extends HttpServlet{
    private static final Logger appointmentLogs = LoggerFactory.getLogger(AppointmentServlet.class);

    private AppointmentService appointmentService = new AppointmentService();
    static final ObjectMapper mapper = new ObjectMapper();

    static  final String responseType ="application/json";

    @Override    // add appointment
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        BufferedReader reader = null;
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
            appointmentLogs.error(" Error on request ", e);
        } finally {
            reader.close();
        }
        appointmentLogs.info("Appointment is fixed");
    }
       //update appointment
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try{
            Appointment appointment = mapper.readValue(req.getInputStream(), Appointment.class);

            appointmentService.updateAppointment(appointment);

            resp.setContentType(responseType);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("Successfully updated the environment");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Appointment update failed");
            resp.getWriter().write(e.getMessage());

        }
    }

          //DeleteAppointmentbyId
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            int appointmentId = Integer.parseInt(req.getParameter("appointmentId"));
            appointmentService.deleteAppointment(appointmentId);

            resp.setContentType(responseType);

            resp.getWriter().write("Appointment deleted successfully");
        } catch(Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Appointment delete failed");
        }
    }

}

