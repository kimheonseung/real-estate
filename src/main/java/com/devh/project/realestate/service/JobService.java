package com.devh.project.realestate.service;

import com.devh.project.realestate.job.ScheduleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    private final ScheduleJob scheduleJob;

    @Autowired
    public JobService(ScheduleJob scheduleJob) {
        this.scheduleJob = scheduleJob;
    }

    public void runScheduleJob() {
        this.scheduleJob.runJob();
    }
}
