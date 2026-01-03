package com.hospital;

import com.hospital.dao.DoctorDao;
import com.hospital.model.Doctor;
import com.hospital.model.Doctor;
import com.hospital.service.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.*;

public class DoctorServiceTest {

    private DoctorDao doctorDao;
    private DoctorService service;

    @BeforeEach
    void setUp() {

        doctorDao = Mockito.mock(DoctorDao.class);

        service = new DoctorService(doctorDao);
    }


    @Test
    void getDoctorByIdTest() {

        Doctor mockDoctor = new Doctor();
        mockDoctor.setDoctorId(1);
        Mockito.when(doctorDao.getDoctorById(1)).thenReturn(mockDoctor);

        Doctor result = service.getDoctorById(1);
        assertNotNull(result);
        assertEquals(1, result.getDoctorId());
    }

    @Test
    void addDoctor() throws Exception {
        Doctor doctor1 = new Doctor();
        doctor1.setDoctorName("doctor");
        service.addDoctor(doctor1);
        // Mockito.verify(doctorDao).addDoctor(doctor1);
        Mockito.verify(doctorDao).addDoctor(doctor1);
    }
}

    
    
   




