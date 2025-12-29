package com.hospital.dao;

import com.hospital.model.Patient;
import com.hospital.util.DBUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;


public class PatientDao {
    public void addPatient(Patient patient) throws Exception {
        String insertPatient = "Insert into patient (patientName, gender, dob, bloodGroup, city, diagnosis, phoneNumber)" +
                " values (?,?,?,?,?,?,?);";
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(insertPatient);
        ps.setString(1, patient.getPatientName());
        ps.setString(2, patient.getGender());
        ps.setDate(3, patient.getDob());
        ps.setString(4, patient.getBloodGroup());
        ps.setString(5, patient.getCity());
        ps.setString(6, patient.getDiagnosis());
        ps.setLong(7, patient.getPhoneNumber());


        ps.executeUpdate();

        ps.close();
        conn.close();
    }
}


