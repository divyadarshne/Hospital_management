package com.hospital;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Appointment;
import com.hospital.model.Appointment;
import com.hospital.service.AppointmentService;
import com.hospital.servlet.AppointmentServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AppointmentServletTest {

    private AppointmentServlet servlet;
    private AppointmentService service;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        service =mock(AppointmentService.class);
        servlet = new AppointmentServlet(); // constructor injection recommended

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        var field = AppointmentServlet.class.getDeclaredField("appointmentService");
        field.setAccessible(true);
        field.set(servlet,service);
    }

    @Test
    void doPostAddAppointmentTest() throws Exception {   //method dopost add appointment

        String json = "{ \"appointmentId\": \"1\" }";

        BufferedReader reader = new BufferedReader(new StringReader(json));
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        Mockito.when(request.getReader()).thenReturn(reader);
        Mockito.when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request, response);
        verify(service).addAppointment(any(Appointment.class));
        verify(response).setStatus(HttpServletResponse.SC_CREATED);

    }

    @Test   //negative testcase
    void doPostAddAppointmentNegativeTest() throws Exception {

        String json = "{\"\": \"\"}";

        BufferedReader reader = new BufferedReader(new StringReader(json));

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        Mockito.when(request.getReader()).thenReturn(reader);
        Mockito.when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request, response);
        //Mockito.verify(service).addAppointment(Mockito.any(Appointment.class)); should not call in negative testcase
        Mockito.verify(service, Mockito.never()).addAppointment(Mockito.any(Appointment.class)); // use this are should not call anything

        Mockito.verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Test
    void doPutUpdateAppointment() throws Exception {

        String json = "{ \"appointmentId\": \"2\"}";
        BufferedReader reader = new BufferedReader(new StringReader(json));
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        Mockito.when(request.getReader()).thenReturn(reader);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPut(request, response);

        verify(service).updateAppointment(any(Appointment.class));
        verify(response).setStatus(HttpServletResponse.SC_CREATED);

    }
    
    @Test
    void doDeleteDeleteAppointmentTest() throws Exception {

        Mockito.when(request.getParameter("appointmentId")).thenReturn("1");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doDelete(request, response);
        verify(service).deleteAppointment(1);
        assertTrue(stringWriter.toString().contains("Appointment deleted successfully"));

    }

}

