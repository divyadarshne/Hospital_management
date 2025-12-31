package com.hospital.model;

import java.util.Date;

public class Appointment {
    private int appointmentId;
    private int patientId;
    private int doctorId;
    private Date appointmentDate;
    private int totalAppointment;
    private int finishedAppointment;


    public int getAppointmentId() {

        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {

        this.appointmentId = appointmentId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public int getTotalAppointment() {
        return totalAppointment;
    }

    public void setTotalAppointment(int totalAppointment) {
        this.totalAppointment = totalAppointment;
    }

    public int getFinishedAppointment() {
        return finishedAppointment;
    }

    public void setFinishedAppointment(int finishedAppointment) {
        this.finishedAppointment = finishedAppointment;
    }
}
