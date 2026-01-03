package com.hospital;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Doctor;
import com.hospital.model.Doctor;
import com.hospital.model.Doctor;
import com.hospital.service.DoctorService;
import com.hospital.service.DoctorService;
import com.hospital.servlet.DoctorServlet;
import com.hospital.servlet.DoctorServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

public class DoctorServletTest {

    private DoctorServlet servlet;
    private DoctorService service;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        service = Mockito.mock(DoctorService.class);
        servlet = new DoctorServlet(service); // constructor injection recommended to inject mock service

        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        var field = DoctorServlet.class.getDeclaredField("doctorService");
        field.setAccessible(true);
        field.set(servlet, service);
    }

    @Test
    void doPostAddDoctorTest() throws Exception {

        String json = "{ \"doctorName\": \"Ravi\" }";

        BufferedReader reader = new BufferedReader(new StringReader(json));

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        Mockito.when(request.getReader()).thenReturn(reader);
        Mockito.when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request, response);

        Mockito.verify(response).setStatus(HttpServletResponse.SC_CREATED);
        Mockito.verify(service).addDoctor(Mockito.any(Doctor.class));
    }
    
    @Test
    void doGetReturnDoctor() throws Exception {

        Doctor doctor = new Doctor();
        doctor.setDoctorName("Ravi");

        Mockito.when(request.getParameter("doctorid")).thenReturn("1");
        Mockito.when(service.getDoctorById(1)).thenReturn(doctor);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);
        Mockito.verify(service).getDoctorById(1);

    }
}