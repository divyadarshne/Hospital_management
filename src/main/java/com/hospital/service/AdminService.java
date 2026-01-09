package com.hospital.service;


import com.hospital.dao.AdminRegisterDao;
import com.hospital.model.Admin;

public class AdminService {
    AdminRegisterDao adminRegisterDao = new AdminRegisterDao();
    public void registeringAdmin(Admin admin) {
       adminRegisterDao.register(admin);
    }
    public void loginAdmin(String username, String password) {
        adminRegisterDao.login( username, password);
    }


}
