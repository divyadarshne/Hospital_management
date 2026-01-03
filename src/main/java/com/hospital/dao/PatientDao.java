package com.hospital.dao;

import com.hospital.model.Patient;

import com.hospital.util.DBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class PatientDao {
    private static final Logger PATIENTDAOLOGS = LoggerFactory.getLogger(PatientDao.class);

    static final int PATIENT_NAME =1;
    static final int GENDER =2;
    static final int DOB =3;
    static final int BLOOD_GROUP =4;
    static final int CITY =5;
    static final int DIOGNOSIS =6;
    static final int PHONE_NUMBER =7;
    static final int PATIENTS_ID =8;


    public void addPatient(Patient patient) throws Exception {

        String insertPatient = "Insert into patient (patient_name, gender, dob, blood_group, city, diagnosis, phone_number)" +
                " values (?,?,?,?,?,?,?);";
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertPatient)){

        ps.setString(PATIENT_NAME, patient.getPatientName());
        ps.setString(GENDER, patient.getGender());
        ps.setDate(DOB, patient.getDob());
        ps.setString(BLOOD_GROUP, patient.getBloodGroup());
        ps.setString(CITY, patient.getCity());
        ps.setString(DIOGNOSIS, patient.getDiagnosis());
        ps.setLong(PHONE_NUMBER, patient.getPhoneNumber());

        PATIENTDAOLOGS.info("Patient added successfully");
        ps.executeUpdate();

    }
    catch (SQLException e) {
        PATIENTDAOLOGS.error("Error while adding patient", e);
        throw e;
    }

    }
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();

        String sql = "SELECT * FROM patient";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Patient p = new Patient();
                p.setPatientId(rs.getInt("patient_id"));
                p.setPatientName(rs.getString("patient_name"));
                p.setGender(rs.getString("gender"));
                p.setDob(rs.getDate("dob"));
                p.setBloodGroup(rs.getString("blood_group"));
                p.setCity(rs.getString("city"));
                p.setDiagnosis(rs.getString("diagnosis"));
                p.setPhoneNumber(rs.getLong("phone_number"));

                patients.add(p);
            }

        } catch (Exception e) {
            PATIENTDAOLOGS.error("Error while Getting all patients", e);
        }
        return patients;
    }

    public Patient getPatientById(int id) {
        String selectsql = "SELECT * FROM patient WHERE patient_id = ?";
        Patient patient=null;

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(selectsql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                patient = new Patient();
                patient.setPatientId(rs.getInt("patient_id"));
                patient.setPatientName(rs.getString("patient_name"));
                patient.setGender(rs.getString("gender"));
                patient.setDob(rs.getDate("dob"));
                patient.setBloodGroup(rs.getString("blood_group"));
                patient.setCity(rs.getString("city"));
                patient.setDiagnosis(rs.getString("diagnosis"));
                patient.setPhoneNumber(rs.getLong("phone_number"));
            }

        } catch (Exception e) {
            PATIENTDAOLOGS.error("Error while getting patient by id", e);
        }
        return patient;
    }

    public Patient updatePatient(Patient patient) {
        String sql = "UPDATE patient SET patient_name=?, gender=?, dob=?, blood_group=?, city=?, diagnosis=?, phone_number=? WHERE patient_id=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(PATIENT_NAME, patient.getPatientName());
            ps.setString(GENDER, patient.getGender());
            ps.setDate(DOB, patient.getDob());
            ps.setString(BLOOD_GROUP, patient.getBloodGroup());
            ps.setString(CITY, patient.getCity());
            ps.setString(DIOGNOSIS, patient.getDiagnosis());
            ps.setLong(PHONE_NUMBER, patient.getPhoneNumber());
            ps.setInt(PATIENTS_ID, patient.getPatientId());

            ps.executeUpdate();
        } catch (Exception e) {
            PATIENTDAOLOGS.error("Error while updating patient", e);
        }
        return patient;
    }
}


