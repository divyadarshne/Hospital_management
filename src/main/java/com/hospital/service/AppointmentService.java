package com.hospital.service;

import com.hospital.dao.AppointmentDao;
import com.hospital.model.Appointment;

import java.util.List;


public class AppointmentService {

    private AppointmentDao appointmentDao =new AppointmentDao();

    public void addAppointment(Appointment appointment) throws Exception {
        appointmentDao.addAppointment(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentDao.getAllAppointments();
    }

    public List<Appointment> getAppointmentsByDoctor(int doctorId) {
        return appointmentDao.getAppointmentsByDoctor(doctorId);
    }

    public Appointment updateAppointment(Appointment appointment) {

        return appointmentDao.updateAppointment(appointment);

    }

    public void deleteAppointment(int appointmentId) {

        appointmentDao.deleteAppointment(appointmentId);

    }


    private AppointmentDao dao;

    public AppointmentService() {       // creating constructor without arguments for other services
        this.dao = new AppointmentDao();
    }

    public AppointmentService(AppointmentDao dao) {   //for constructer called in tests
        this.dao = dao;
    }

}

