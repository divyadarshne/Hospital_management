package com.hospital.scheduling;

import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Properties;

public class SchedulerMain {

    public static void main(String[] args) throws SchedulerException {

        // Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        Properties props = new Properties();
        props.setProperty("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        props.setProperty("org.quartz.threadPool.threadCount", "3");
        props.setProperty("org.quartz.threadPool.threadPriority", "5");
        StdSchedulerFactory factory = new StdSchedulerFactory(props);
        Scheduler scheduler = factory.getScheduler();

        JobDetail job = JobBuilder.newJob(Jobs.class)
                .withIdentity("Jobs", "group1")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("appointmentDateTrigger", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 20 * * * ?"))
                .build();

//        JobDetail job2 = JobBuilder.newJob(MyJob.class)
//                .withIdentity("Myjob", "group1")
//                .build();
//
//        Trigger trigger2 = TriggerBuilder.newTrigger()
//                .withIdentity("checkTrigger", "group1")
//                .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?"))
//                .build();

        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }


}


