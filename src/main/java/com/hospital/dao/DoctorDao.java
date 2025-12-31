package com.hospital.dao;

import com.hospital.model.Appointment;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;
import com.hospital.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DoctorDao {

    public void addDoctor(Doctor doctor) throws Exception {
        String insertPatient = "Insert into doctor (doctor_name, specialization)" +
                " values (?,?);";
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(insertPatient);
        ps.setString(1, doctor.getDoctorName());
        ps.setString(2, doctor.getSpecialization());
        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public Doctor getDoctorById(int id) {
        String sql = "SELECT * FROM doctor WHERE doctor_id = ?";
        Doctor doc=null;

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                doc = new Doctor();
                doc.setDoctorId(rs.getInt("doctor_id"));
                doc.setDoctorName(rs.getString("doctor_name"));
                doc.setSpecialization(rs.getString("specialization"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

}
