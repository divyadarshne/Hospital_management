package com.hospital.service;

import com.hospital.dao.AppointmentDao;
import com.hospital.model.Appointment;


public class AppointmentService {

    private AppointmentDao appointmentDao =new AppointmentDao();

    public void addAppointment(Appointment appointment) throws Exception {
        appointmentDao.addAppointment(appointment);
    }
}

