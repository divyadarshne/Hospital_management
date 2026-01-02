package com.hospital.service;


import com.hospital.dao.DoctorDao;
import com.hospital.model.Doctor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DoctorService {

    private final DoctorDao doctorDao =new DoctorDao();
    private static final Logger DOCTORSERVICELOGS = LoggerFactory.getLogger(DoctorService.class);

    public void addDoctor(Doctor doctor) throws Exception {
        DOCTORSERVICELOGS.info("adding doctor");
        doctorDao.addDoctor(doctor);
    }

    public Doctor getDoctorById(int id) {
        DOCTORSERVICELOGS.info("getting Doctor by stream");
        return doctorDao.getDoctorById(id);
    }

    private DoctorDao dao;

     public DoctorService() {       // creating constructor without arguments for other services
        this.dao = new DoctorDao();
    }
    public DoctorService(DoctorDao dao) {   //for constructer called in tests
        this.dao = dao;
    }

}
