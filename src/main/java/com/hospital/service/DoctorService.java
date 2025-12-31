package com.hospital.service;


import com.hospital.dao.DoctorDao;
import com.hospital.model.Doctor;

public class DoctorService {

    private DoctorDao doctorDao =new DoctorDao();

    public void addDoctor(Doctor doctor) throws Exception {
        doctorDao.addDoctor(doctor);
    }

    public Doctor getDoctorById(int id) {
        return doctorDao.getDoctorById(id);
    }

}
