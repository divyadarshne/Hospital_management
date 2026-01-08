package com.hospital.util;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

@WebListener
public class ConnClass implements ServletContextListener {

    private static HikariDataSource DATASOURCE;
    HikariConfig hconfig = new HikariConfig();
    Logger logs = LoggerFactory.getLogger(ConnClass.class);

    public void ContextInitializer(ServletContextEvent servletContextEvent) throws ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream("C:\\Users\\blues\\inteliJ_workspace\\appointments\\src\\main\\resources\\Scheduler.properties")) {
            Properties dbprops = new Properties();
            dbprops.load(fis);

            hconfig.setDriverClassName(dbprops.getProperty("driver"));
            hconfig.setJdbcUrl(dbprops.getProperty("url"));
            hconfig.setUsername(dbprops.getProperty("username"));
            hconfig.setPassword(dbprops.getProperty("password"));
            hconfig.setMaximumPoolSize(Integer.parseInt(dbprops.getProperty("Hikari.maximumPool")));

            logs.info("HicaryCP configured ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DATASOURCE = new HikariDataSource(hconfig);
    }
    public static Connection getConnection () throws Exception {
        return DATASOURCE.getConnection();
    }
}