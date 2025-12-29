package com.hospital.dao;

import com.hospital.model.Patient;
import com.hospital.util.DBUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


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
//    public void getPatientDetails(int patientId) throws Exception{
//        String getPatient = "Select * from patient where patientId = ? ";
//
//       Connection con = DBUtil.getConnection();
//       PreparedStatement ps = con.prepareStatement(getPatient);
//
//        ps.setInt(1, patientId);
//        ResultSet rs1=ps.executeQuery();
//        Patient patient1 = new Patient();
//
//        if(rs1.next()){
//            patient1.setPatientId(rs1.getInt("patientId"));
//            patient1.setPatientName(rs1.getString("patientName"));
//            patient1.setGender(rs1.getString("gender"));
//            patient1.setDob(rs1.getDate(""));
//
//        }
//
//
//        }
}


