package com.hospital.dao;

import com.hospital.model.Doctor;
import com.hospital.model.Patient;
import com.hospital.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DoctorDao {

    public void addDoctor(Doctor doctor) throws Exception {
        String insertPatient = "Insert into doctor (doctorName, specialization)" +
                " values (?,?);";
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(insertPatient);
        ps.setString(1, doctor.getDoctorName());
        ps.setString(2, doctor.getSpecialization());
        ps.executeUpdate();

        ps.close();
        conn.close();
    }
}
