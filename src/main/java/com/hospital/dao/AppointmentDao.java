package com.hospital.dao;

import com.hospital.exceptions.DataAccessException;
import com.hospital.model.Appointment;
import com.hospital.util.ConnClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDao {
    private static final Logger APPOINTMENTDAOLOGGER = LoggerFactory.getLogger(AppointmentDao.class);

    static final int PATIENTS_ID =1;
    static final int DOCTORS_ID =2;
    static final int APPOINTMENT_DATE =3;
    static final int T_APPOINTMENT =4;
    static final int F_APPOINTMENT =5;
    static final int APPOINTMENT_ID =6;


    public void addAppointment(Appointment appointment) throws SQLException {


        String insertappointment = "INSERT INTO appointment (patient_id, doctor_id, appointment_date, total_appointment, finished_appointment) " +
                "VALUES (?, ?, ?, ?, ?)";

        try( Connection conn = ConnClass.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertappointment)){
        ps.setInt(PATIENTS_ID , appointment.getPatientId());
        ps.setInt(DOCTORS_ID, appointment.getDoctorId());
        ps.setDate(APPOINTMENT_DATE, new java.sql.Date(
                appointment.getAppointmentDate().getTime()));
        ps.setInt(T_APPOINTMENT, appointment.getTotalAppointment());
        ps.setInt(F_APPOINTMENT, appointment.getFinishedAppointment());

        ps.executeUpdate();

        } catch (Exception e) {
            throw new DataAccessException("SQl Error Occured: ",e);
        }
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT * FROM appointment";

        try (Connection con = ConnClass.getConnection();
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
            APPOINTMENTDAOLOGGER.error("Error While getting all appointments", e);
        }
        return list;
    }


    public List<Appointment> getAppointmentsByDoctor(int doctorId) {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT * FROM appointment WHERE doctor_id = ?";

        try (Connection con = ConnClass.getConnection();
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
            APPOINTMENTDAOLOGGER.error("Error While getting all appointments By Doctor Id",e);

        }
        return list;
    }

    public Appointment updateAppointment(Appointment appointment) {
        String sql = "UPDATE appointment SET patient_id=?, doctor_id=?, appointment_date=?, total_appointment=?, finished_appointment=? WHERE appointment_id=?";

        try (Connection con = ConnClass.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(PATIENTS_ID, appointment.getPatientId());
            ps.setInt(DOCTORS_ID, appointment.getDoctorId());
            ps.setDate(APPOINTMENT_DATE, new java.sql.Date(
                    appointment.getAppointmentDate().getTime()));
            ps.setInt(T_APPOINTMENT, appointment.getTotalAppointment());
            ps.setInt(F_APPOINTMENT, appointment.getFinishedAppointment());
            ps.setInt(APPOINTMENT_ID, appointment.getAppointmentId());

            ps.executeUpdate();
        } catch (Exception e) {
            APPOINTMENTDAOLOGGER.error("Error While updating appointments By Appointment Id",e);
        }
        return appointment;
    }

    public void deleteAppointment(int appointmentId) {
        String sql = "DELETE FROM appointment WHERE appointment_id=?";

        try (Connection con =ConnClass.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, appointmentId);
            ps.executeUpdate();

        } catch (Exception e) {
            APPOINTMENTDAOLOGGER.error("Error While Deleting appointments By Appointment Id",e);

        }

    }

    public void executeUpdateAppointmentJob () {

        String sqlJob = " UPDATE appointment SET total_appointment = total_appointment-1 WHERE appointment_date < curdate() and total_appointment > 0;";

        try (Connection con = ConnClass.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlJob)) {
            APPOINTMENTDAOLOGGER.info("Deleting the expired appointment");
            APPOINTMENTDAOLOGGER.info(" running {} " ,  LocalDate.now());
                    ps.executeUpdate();
                    APPOINTMENTDAOLOGGER.info(" executed at {} " ,  LocalDate.now());

        } catch (Exception e) {
            APPOINTMENTDAOLOGGER.error(" ERROR while deleting the date {} " ,  LocalDate.now());
            throw new RuntimeException(e);
        }

    }

}

