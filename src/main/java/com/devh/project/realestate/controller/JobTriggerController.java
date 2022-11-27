package com.devh.project.realestate.controller;

import com.devh.project.realestate.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/job/trigger")
public class JobTriggerController {
    private final JobService jobService;

    @Autowired
    public JobTriggerController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/schedule-job")
    public boolean triggerScheduleJob() {
        this.jobService.runScheduleJob();
        return true;
    }
}
