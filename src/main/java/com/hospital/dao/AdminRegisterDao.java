package com.hospital.dao;

import com.hospital.model.Admin;
import com.hospital.util.DBConnectionUtil;
import com.hospital.util.PasswordHashingUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminRegisterDao {

    public void register(Admin admin) {

        String hashedPassword = PasswordHashingUtil.hashPassword(admin.getPassword());
        String sql = "INSERT INTO admin (name, userName, email, password_hash) VALUES (?, ?, ?,?)";
        try (Connection con = DBConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, admin.getName());
            ps.setString(2, admin.getUserName());
            ps.setString(3, admin.getEmail());
            ps.setString(4, hashedPassword);

            ps.executeUpdate();
            //log execution
        } catch (Exception e) {
            throw new RuntimeException(e);
            //log warn
        }
    }

    public void login(String username, String password) {

        try (Connection con = DBConnectionUtil.getConnection()) {

            String sql = "SELECT admin_id, username, email, password_hash FROM admin WHERE username = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                throw new Exception("Invalid credentials");
            }

            String storedHash = rs.getString("password_hash");

            if (!PasswordHashingUtil.verifyPassword(password, storedHash)) {
                throw new Exception("Invalid credentials");
            }

            // Authentication success means
            Admin admin = new Admin();
            admin.setAdminId(rs.getInt("admin_id"));
            admin.setName(rs.getString("name"));
            admin.setUserName(rs.getString("username"));
            admin.setEmail(rs.getString("email"));
            admin.setPassword(storedHash);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
