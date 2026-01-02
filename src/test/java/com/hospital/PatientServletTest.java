package com.hospital;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Patient;
import com.hospital.service.PatientService;
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

class PatientServletTest {

    private PatientServlet servlet;
    private PatientService service;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(PatientService.class);
        servlet = new PatientServlet(service); // constructor injection recommended

        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
    }

    @Test
    void doPost_shouldReturnCreatedStatus() throws Exception {

        String json = "{ \"patientName\": \"Ravi\" }";

        BufferedReader reader =
                new BufferedReader(new StringReader(json));

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        Mockito.when(request.getReader()).thenReturn(reader);
        Mockito.when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request, response);

        Mockito.verify(response).setStatus(HttpServletResponse.SC_CREATED);
        Mockito.verify(service).addPatient(Mockito.any(Patient.class));
    }
}
