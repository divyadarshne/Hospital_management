package com.hospital.service;

import com.hospital.dao.AppointmentDao;
import com.hospital.model.Appointment;

public class AppointmentService {
    public void addAppointment(Appointment appointment) throws Exception {
        AppointmentDao.addAppointment(appointment);
    }
}

