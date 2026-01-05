package com.hospital.scheduling;

import com.hospital.dao.AppointmentDao;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Jobs implements Job {

    AppointmentDao dao = new AppointmentDao();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        dao.executeUpdateAppointmentJob();

    }
}
