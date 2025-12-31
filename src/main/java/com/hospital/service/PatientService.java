package com.hospital.service;

import com.hospital.dao.PatientDao;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;

import java.util.List;

public class PatientService {
 private final PatientDao patientDao =new PatientDao();

  public void addPatient (Patient patient) throws Exception {

     if (patient.getPatientName()== null || patient.getPatientName().isEmpty()) {
         throw new Exception("Patient name is required");
     }
     patientDao.addPatient(patient);
     }

     public List<Patient> getAllPatients() {
      return patientDao.getAllPatients();
  }

    public Patient getPatientById(int patientid) {
        return patientDao.getPatientById(patientid);
    }

    public Patient updatePatient(Patient patient) {
        return patientDao.updatePatient(patient);
    }
}


