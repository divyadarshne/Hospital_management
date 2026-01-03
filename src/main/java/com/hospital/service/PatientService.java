package com.hospital.service;

import com.hospital.dao.PatientDao;
import com.hospital.model.Patient;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PatientService {
    private PatientDao patientDao = new PatientDao();
    private static final Logger log = LoggerFactory.getLogger(PatientService.class);

    public PatientService () {
        this.patientDao = new PatientDao();
    }

    public PatientService(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    public void addPatient (Patient patient) throws Exception {

     if (patient.getPatientName()== null || patient.getPatientName().isEmpty()) {
         throw new Exception("Patient name is required");
     }
      log.info("Adding patient");

      patientDao.addPatient(patient);
     }

     public List<Patient> getAllPatients() {
         log.info("Fetching all patients");
         return patientDao.getAllPatients();

  }

    public Patient getPatientById(int patientid) {
        log.info("Fetching patient with id {}", patientid);
        return patientDao.getPatientById(patientid);
    }

    public Patient updatePatient(Patient patient) {
        log.info("updating patient");
        return patientDao.updatePatient(patient);

    }
}


