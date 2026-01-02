package com.hospital.service;

import com.hospital.dao.AppointmentDao;
import com.hospital.model.Appointment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class AppointmentService {

    private final AppointmentDao appointmentDao =new AppointmentDao();

    private static final Logger APPOINTMENTSERVICELOGS = LoggerFactory.getLogger(AppointmentService.class);


    public void addAppointment(Appointment appointment) throws Exception {
        APPOINTMENTSERVICELOGS.info("adding appointment");
        appointmentDao.addAppointment(appointment);
    }

    public List<Appointment> getAllAppointments() {
        APPOINTMENTSERVICELOGS.info("Getting all Appointments");
        return appointmentDao.getAllAppointments();
    }

    public List<Appointment> getAppointmentsByDoctor(int doctorId) {
        APPOINTMENTSERVICELOGS.info("getting appointment by doctor id");
        return appointmentDao.getAppointmentsByDoctor(doctorId);
    }

    public Appointment updateAppointment(Appointment appointment) {
        APPOINTMENTSERVICELOGS.info("updating appointment");
        return appointmentDao.updateAppointment(appointment);

    }

    public void deleteAppointment(int appointmentId) {
        APPOINTMENTSERVICELOGS.info("Deleting appointment by appointment Id");
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

