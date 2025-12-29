package com.hospital.service;

import com.hospital.dao.PatientDao;
import com.hospital.model.Patient;

public class PatientService {
 private PatientDao patientDao =new PatientDao();

 public void addPatient (Patient patient) throws Exception {

     if (patient.getPatientName()== null || patient.getPatientName().isEmpty()) {
         throw new Exception("Patient name is required");
     }
     patientDao.addPatient(patient);
     }
 }

