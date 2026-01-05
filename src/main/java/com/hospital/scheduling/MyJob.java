package com.hospital.scheduling;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.time.LocalDateTime;


public class MyJob implements Job {

    public void execute(JobExecutionContext context){
        System.out.println("hii " + LocalDateTime.now());
    }
}
