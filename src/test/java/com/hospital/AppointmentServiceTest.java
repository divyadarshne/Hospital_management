package com.hospital;

import com.hospital.dao.AppointmentDao;
import com.hospital.model.Appointment;
import com.hospital.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AppointmentServiceTest {
    private AppointmentDao  daoMock;
    private AppointmentService serviceMock;

    @BeforeEach  //arranged the class
    void setup() throws Exception {
        daoMock = mock(AppointmentDao.class);
        serviceMock = new AppointmentService(daoMock);

    }
    @Test
    void testGetAllAppointments() {

        Appointment a = new Appointment();
        List<Appointment> result = serviceMock.getAllAppointments();  //act calling the method should be tested
        // assert the acutal output with expected output checking the list is not null.
        assertNotNull(result, "The returned list should not be null");
    }

//    @Test
//    void testGetAppointmentsByDoctor() {
//
//        Mockito.when(daoMock.getAppointmentsByDoctor(2))
//                .thenReturn(List.of(new Appointment()));
//
//        assertEquals(1, serviceMock.getAppointmentsByDoctor(2).size());
//    }
}
