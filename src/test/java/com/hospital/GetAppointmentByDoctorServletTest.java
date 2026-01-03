package com.hospital;

import com.hospital.model.Appointment;
import com.hospital.service.AppointmentService;
import com.hospital.servlet.GetAppointmentsByDoctorServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static org.mockito.Mockito.mock;

public class GetAppointmentByDoctorServletTest {
    private GetAppointmentsByDoctorServlet servlet;
    private AppointmentService service;

    private HttpServletRequest request;
    private HttpServletResponse response;
    
    @BeforeEach
    void setup(){
        servlet = new GetAppointmentsByDoctorServlet();
        service = mock(AppointmentService.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }
    
    @Test
    void doGetAppointmentsByDoctortest() throws IOException {
        Appointment appointment = new Appointment();
        appointment.setDoctorId(2);

        Mockito.when(request.getParameter("doctorId")).thenReturn("2");
        Mockito.when(service.getAppointmentsByDoctor(2)).thenReturn(List.of(appointment));

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);
        Mockito.verify(response).setContentType("application/json");

    }
        
}
    
    
    

