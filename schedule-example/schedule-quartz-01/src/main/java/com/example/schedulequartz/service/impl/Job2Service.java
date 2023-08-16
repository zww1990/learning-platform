package com.example.schedulequartz.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

import java.time.LocalDateTime;

@Slf4j
public class Job2Service implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            log.info("Job2Service -- {} -- {}", context.getScheduler().getSchedulerInstanceId(), LocalDateTime.now());
        } catch (SchedulerException e) {
            log.error(e.getLocalizedMessage(), e);
        }
    }
}
