package com.hospital.scheduling;

import com.hospital.dao.AppointmentDao;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.time.LocalDateTime;
import java.util.Properties;

public class SchedulerMain  implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Logger schedulelogger = LoggerFactory.getLogger(SchedulerMain.class);
        // Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        AppointmentDao dao = new AppointmentDao();
        dao.executeUpdateAppointmentJob();
        Properties props = new Properties();
        props.setProperty("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        props.setProperty("org.quartz.threadPool.threadCount", "3");
        props.setProperty("org.quartz.threadPool.threadPriority", "5");
        try {
            StdSchedulerFactory factory = new StdSchedulerFactory(props);
            Scheduler scheduler = factory.getScheduler();

            JobDetail job = JobBuilder.newJob(Jobs.class)
                    .withIdentity("Jobs", "group1")
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("appointmentDateTrigger", "group1")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 08 10  * * ?")).build();

            scheduler.start();
            schedulelogger.info("Started the task" + LocalDateTime.now());

            scheduler.scheduleJob(job, trigger);
            schedulelogger.info("completed the job" + LocalDateTime.now());
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
}




