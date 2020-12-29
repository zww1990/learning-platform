package com.example.springschedule.service.impl;

import java.time.LocalDateTime;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Job1Service implements Job {
	private static final Logger log = LoggerFactory.getLogger(Job1Service.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			log.info("Job1Service -- {} -- {}", context.getScheduler().getSchedulerInstanceId(), LocalDateTime.now());
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
