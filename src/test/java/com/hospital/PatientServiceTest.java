package com.hospital;

import com.hospital.dao.PatientDao;
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
        // 1️⃣ Create a MOCK (fake) DAO — no DB connection
        patientDao = Mockito.mock(PatientDao.class);

        // 2️⃣ Inject mock DAO into service
        service = new PatientService(patientDao);
    }


    // 1️⃣ Validation test
    @Test
    void addPatient_shouldFail_whenNameIsNull() {

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

    // 2️⃣ Delegation test
    @Test
    void addPatient_shouldCallDao_whenValidPatient() throws Exception {

        Patient patient = new Patient();
        patient.setPatientName("Ravi");

        service.addPatient(patient);

        Mockito.verify(patientDao).addPatient(patient);
    }

    // 3️⃣ getAllPatients delegation
    @Test
    void getAllPatients_shouldReturnListFromDao() {

        List<Patient> mockList = new ArrayList<>();
        Mockito.when(patientDao.getAllPatients()).thenReturn(mockList);

        List<Patient> result = service.getAllPatients();

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    // 4️⃣ getPatientById delegation
    @Test
    void getPatientById_shouldReturnPatient() {

        Patient mockPatient = new Patient();
        mockPatient.setPatientId(1);

        Mockito.when(patientDao.getPatientById(1)).thenReturn(mockPatient);

        Patient result = service.getPatientById(1);

        assertNotNull(result);
        assertEquals(1, result.getPatientId());
    }
}


