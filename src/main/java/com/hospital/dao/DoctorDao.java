package com.hospital.dao;


import com.hospital.model.Doctor;
import com.hospital.util.DBConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorDao {
    private static final Logger DOCTORDAOLOGGER = LoggerFactory.getLogger(DoctorDao.class);
    private static final int PARAMINDEXONE = 1;
    private static final int PARAMINDEXTWO = 2;

    public void addDoctor(Doctor doctor) throws SQLException {
        String insertPatient = "Insert into doctor (doctor_name, specialization)" +
                " values (?,?);";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertPatient)) {

            ps.setString(PARAMINDEXONE, doctor.getDoctorName());
            ps.setString(PARAMINDEXTWO, doctor.getSpecialization());
            ps.executeUpdate();

        } catch (Exception e) {
            String message = "Exception raised while adding user: " + e.getMessage();
            DOCTORDAOLOGGER.warn(message);
        }

        DOCTORDAOLOGGER.info("Doctor added successfully");

    }

    public Doctor getDoctorById(int id) {
        String sqlget = "SELECT * FROM doctor WHERE doctor_id = ?";
        Doctor doc = null;

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlget)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                doc = new Doctor();
                doc.setDoctorId(rs.getInt("doctor_id"));
                doc.setDoctorName(rs.getString("doctor_name"));
                doc.setSpecialization(rs.getString("specialization"));
            }

        } catch (Exception e) {
            String message = "Exception raised while authenticating user: " + e.getMessage();
            DOCTORDAOLOGGER.warn(message);
        }
            return doc;

    }
}