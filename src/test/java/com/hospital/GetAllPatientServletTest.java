package com.hospital;
import com.hospital.model.Patient;
import com.hospital.service.PatientService;
import com.hospital.servlet.GetAllPatientServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static org.mockito.Mockito.mock;

class GetAllPatientServletTest {

    private GetAllPatientServlet servlet;
    private PatientService mockService;

    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    void setup() throws Exception {

        servlet = new GetAllPatientServlet();

        mockService = mock(PatientService.class);

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        StringWriter sw = new StringWriter();
        Mockito.when(response.getWriter())
                .thenReturn(new PrintWriter(sw));
    }
    @Test
    void testDoGet() throws Exception {

        Patient a = new Patient();
        a.setPatientId(1);

        Mockito.when(mockService.getAllPatients())
                .thenReturn(List.of(a));

        servlet.doGet(request, response);

        Mockito.verify(response)
                .setContentType("application/json");
    }
}