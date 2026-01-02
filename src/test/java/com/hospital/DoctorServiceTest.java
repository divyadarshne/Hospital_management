package com.hospital;

import com.hospital.dao.DoctorDao;
import com.hospital.model.Doctor;
import com.hospital.service.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.*;

public class DoctorServiceTest {

    private DoctorDao DoctorDao;
    private DoctorService service;

    @BeforeEach
    void setUp() {

        DoctorDao = Mockito.mock(DoctorDao.class);

        service = new DoctorService(DoctorDao);
    }


    @Test
    void getDoctorById_shouldReturnDoctor() {

        Doctor mockDoctor = new Doctor();
        mockDoctor.setDoctorId(1);

        Mockito.when(DoctorDao.getDoctorById(1)).thenReturn(mockDoctor);

        Doctor result = service.getDoctorById(1);

        assertNotNull(result);
        assertEquals(1, result.getDoctorId());
    }
}


