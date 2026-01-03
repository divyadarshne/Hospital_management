package com.hospital;

import com.hospital.dao.PatientDao;
import com.hospital.model.Appointment;
import com.hospital.model.Patient;
import com.hospital.model.Patient;
import com.hospital.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PatientServiceTest {


    private PatientDao patientDao;
    private PatientService service;

    @BeforeEach
    void setUp() {
        patientDao = Mockito.mock(PatientDao.class);
        service = new PatientService(patientDao);
    }

    @Test
    void addPatientFail() {

        Patient patient = new Patient();
        patient.setPatientName(null);

        Exception exception = null;
        try {
            service.addPatient(patient);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals("Patient name is required", exception.getMessage());
    }

    @Test
    void addPatientTest() throws Exception {

        Patient patient = new Patient();
        patient.setPatientName("Ravi");

        service.addPatient(patient);

        Mockito.verify(patientDao).addPatient(patient);
    }

    @Test
    void getAllPatientsTest() {

        List<Patient> mockList = new ArrayList<>();
        Mockito.when(patientDao.getAllPatients()).thenReturn(mockList);

        List<Patient> result = service.getAllPatients();

        assertNotNull(result);
        assertEquals(0, result.size());
    }


    @Test
    void getPatientById() {

        Patient mockPatient = new Patient();
        mockPatient.setPatientId(1);

        Mockito.when(patientDao.getPatientById(1)).thenReturn(mockPatient);
        Patient result = service.getPatientById(1);

        assertNotNull(result);
        assertEquals(1, result.getPatientId());
    }

    @Test
    void updatePatientTest() {
        Patient patient = new Patient();

        Mockito.when(patientDao.updatePatient(patient)).thenReturn(patient);

        Patient result = service.updatePatient(patient);
        assertNotNull(result);

    }

}


