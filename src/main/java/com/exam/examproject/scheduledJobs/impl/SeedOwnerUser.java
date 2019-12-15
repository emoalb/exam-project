package com.exam.examproject.scheduledJobs.impl;


import com.exam.examproject.scheduledJobs.ScheduledJob;
import com.exam.examproject.services.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SeedOwnerUser implements ScheduledJob {
private final AuthService authService;

@Autowired
    public SeedOwnerUser(AuthService authService) {
        this.authService = authService;
    }

    @Override
    @Scheduled(fixedRate = 100000)
    public void scheduledJob() {
        this.authService.SeedModerator();
    }
}
