package com.hospital.dao;

import com.hospital.model.Patient;

import com.hospital.util.DBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



public class PatientDao {
    private static final Logger patientDaoLogs = LoggerFactory.getLogger(PatientDao.class);

    public void addPatient(Patient patient) throws Exception {

        String insertPatient = "Insert into patient (patient_name, gender, dob, blood_group, city, diagnosis, phone_number)" +
                " values (?,?,?,?,?,?,?);";
        try{
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(insertPatient);
        ps.setString(1, patient.getPatientName());
        ps.setString(2, patient.getGender());
        ps.setDate(3, patient.getDob());
        ps.setString(4, patient.getBloodGroup());
        ps.setString(5, patient.getCity());
        ps.setString(6, patient.getDiagnosis());
        ps.setLong(7, patient.getPhoneNumber());

        patientDaoLogs.info("Patient added successfully");
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
    catch (Exception e) {
        patientDaoLogs.error("Error while adding patient", e);
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
            patientDaoLogs.error("Error while updating patient", e);
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
            patientDaoLogs.error("Error while updating patient", e.getMessage());
        }
        return patient;
    }

    public Patient updatePatient(Patient patient) {
        String sql = "UPDATE patient SET patient_name=?, gender=?, dob=?, blood_group=?, city=?, diagnosis=?, phone_number=? WHERE patient_id=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, patient.getPatientName());
            ps.setString(2, patient.getGender());
            ps.setDate(3, patient.getDob());
            ps.setString(4, patient.getBloodGroup());
            ps.setString(5, patient.getCity());
            ps.setString(6, patient.getDiagnosis());
            ps.setLong(7, patient.getPhoneNumber());
            ps.setInt(8, patient.getPatientId());

            ps.executeUpdate();
        } catch (Exception e) {
            patientDaoLogs.error("Error while updating patient", e);
        }
        return patient;
    }
}


