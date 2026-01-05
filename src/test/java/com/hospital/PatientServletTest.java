package com.hospital;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Patient;
import com.hospital.model.Patient;
import com.hospital.service.PatientService;
import com.hospital.servlet.AppointmentServlet;
import com.hospital.servlet.PatientServlet;
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
import static org.mockito.Mockito.verify;

class PatientServletTest {

    private PatientServlet servlet;
    private PatientService service;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        service = Mockito.mock(PatientService.class);
        servlet = new PatientServlet(service); // constructor injection recommended

        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);

        var field = PatientServlet.class.getDeclaredField("patientService");
        field.setAccessible(true);
        field.set(servlet, service);
    }

    @Test
    void doPostAddPatientTest() throws Exception {

        String json = "{ \"patientName\": \"Ravi\" }";

        BufferedReader reader = new BufferedReader(new StringReader(json));

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        Mockito.when(request.getReader()).thenReturn(reader);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);

        Mockito.verify(response).setStatus(HttpServletResponse.SC_CREATED);
        Mockito.verify(service).addPatient(Mockito.any(Patient.class));
    }

    @Test
        //negative testcase
    void doPostAddPatientNegativeTest() throws Exception {
        String json = "{ \"patientName\":\" \"}";

    BufferedReader reader = new BufferedReader(new StringReader(json));
    StringWriter stringWriter = new StringWriter();
    PrintWriter writer = new PrintWriter(stringWriter);
    try {
    Mockito.when(request.getReader()).thenReturn(reader);
    Mockito.when(response.getWriter()).thenReturn(writer);

    servlet.doPost(request, response);

    Mockito.verify(service).addPatient(Mockito.any(Patient.class));
     }catch(Exception e) {
    Mockito.verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    assertNotNull(e);
     }
    }

    @Test
    void doGetReturnPatient() throws Exception {
        Patient patient = new Patient();
        patient.setPatientName("Ravi");

        Mockito.when(request.getParameter("patientid")).thenReturn("1");
        Mockito.when(service.getPatientById(1)).thenReturn(patient);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);
        Mockito.verify(service).getPatientById(1);

    }

    @Test
    void doPutUpdatePatient() throws Exception {

        String json = "{ \"patientId\": \"2\"}";
        BufferedReader reader = new BufferedReader(new StringReader(json));
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        Mockito.when(request.getReader()).thenReturn(reader);
        Mockito.when(response.getWriter()).thenReturn(writer);
        servlet.doPut(request, response);

        verify(service).updatePatient(any(Patient.class));
        verify(response).setStatus(HttpServletResponse.SC_CREATED);

    }


}
