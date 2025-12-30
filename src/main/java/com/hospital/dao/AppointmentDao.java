package com.hospital.dao;

import com.hospital.model.Appointment;
import com.hospital.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDao {
    public static void addAppointment(Appointment appointment) throws Exception {
        String insertappointment = "INSERT INTO appointment (patient_id, doctor_id, appointment_date, total_appointment, finished_appointment) " +
                "VALUES (?, ?, ?, ?, ?)";
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(insertappointment);

        ps.setInt(1, appointment.getPatientId());
        ps.setInt(2, appointment.getDoctorId());
        ps.setDate(3, new java.sql.Date(
                appointment.getAppointmentDate().getTime()));
        ps.setInt(4, appointment.getTotalAppointment());
        ps.setInt(5, appointment.getFinishedAppointment());

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT * FROM appointment";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Appointment a = new Appointment();
                a.setAppointmentId(rs.getInt("appointment_id"));
                a.setPatientId(rs.getInt("patient_id"));
                a.setDoctorId(rs.getInt("doctor_id"));
                a.setAppointmentDate(rs.getDate("appointment_date"));
                a.setTotalAppointment(rs.getInt("total_appointment"));
                a.setFinishedAppointment(rs.getInt("finished_appointment"));
                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public List<Appointment> getAppointmentsByDoctor(int doctorId) {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT * FROM appointment WHERE doctor_id = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Appointment a = new Appointment();
                a.setAppointmentId(rs.getInt("appointment_id"));
                a.setPatientId(rs.getInt("patient_id"));
                a.setDoctorId(rs.getInt("doctor_id"));
                a.setAppointmentDate(rs.getDate("appointment_date"));
                a.setTotalAppointment(rs.getInt("total_appointment"));
                a.setFinishedAppointment(rs.getInt("finished_appointment"));
                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}

