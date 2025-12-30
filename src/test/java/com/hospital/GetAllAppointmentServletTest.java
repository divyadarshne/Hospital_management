package com.hospital;
import com.hospital.model.Appointment;
import com.hospital.service.AppointmentService;
import com.hospital.servlet.AppointmentServlet;
import com.hospital.servlet.GetAllAppointmentServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class GetAllAppointmentServletTest {

    private GetAllAppointmentServlet servlet;
    private AppointmentService mockService;

    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    void setup() throws Exception {

        servlet = new GetAllAppointmentServlet();

        mockService = mock(AppointmentService.class);

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        StringWriter sw = new StringWriter();
        Mockito.when(response.getWriter())
                .thenReturn(new PrintWriter(sw));
    }
    @Test
    void testDoGet() throws Exception {

        Appointment a = new Appointment();
        a.setAppointmentId(1);

        Mockito.when(mockService.getAllAppointments())
                .thenReturn(List.of(a));

        servlet.doGet(request, response);

        Mockito.verify(response)
                .setContentType("application/json");
    }
}