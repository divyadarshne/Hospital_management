package com.hospital.dao;

import com.hospital.model.Appointment;
import com.hospital.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AppointmentDao {
    public static void addAppointment(Appointment appointment) throws Exception {
        String insertappointment = "INSERT INTO appointment (patientId, doctorId, nextAppointmentDate, totalAppointment, finishedAppointment) " +
                "VALUES (?, ?, ?, ?, ?)";
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(insertappointment);

        ps.setInt(1, appointment.getPatientId());
        ps.setInt(2, appointment.getDoctorId());
        ps.setDate(3, new java.sql.Date(
                appointment.getNextAppointmentDate().getTime()));
        ps.setInt(4, appointment.getTotalAppointment());
        ps.setInt(5, appointment.getFinishedAppointment());

        ps.executeUpdate();

        ps.close();
        conn.close();
    }
}

