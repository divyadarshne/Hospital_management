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
@Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
    try {
        Properties dbprops = new Properties();
        dbprops.load(getClass().getClassLoader().getResourceAsStream("Application.properties"));

        hconfig.setDriverClassName(dbprops.getProperty("driver"));
        hconfig.setJdbcUrl(dbprops.getProperty("url"));
        hconfig.setUsername(dbprops.getProperty("username"));
        hconfig.setPassword(dbprops.getProperty("password"));
        hconfig.setMaximumPoolSize(Integer.parseInt(dbprops.getProperty("Hikari.maximumPool")));

        logs.info("HikariCP initialized successfully");

    } catch (Exception e) {
        logs.error("Failed to initialize datasource", e);
    }
    DATASOURCE = new HikariDataSource(hconfig);
    servletContextEvent.getServletContext().setAttribute("dataSource", DATASOURCE);
}
    public static Connection getConnection() throws Exception {
        if (DATASOURCE == null) {
            throw new IllegalStateException("Datasource not initialized");
        }
        return DATASOURCE.getConnection();
    }

}