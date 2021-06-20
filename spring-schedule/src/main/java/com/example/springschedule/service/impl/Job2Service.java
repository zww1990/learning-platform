package com.example.springschedule.service.impl;

import java.time.LocalDateTime;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Job2Service implements Job {
	private static final Logger log = LoggerFactory.getLogger(Job2Service.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			log.info("Job2Service -- {} -- {}", context.getScheduler().getSchedulerInstanceId(), LocalDateTime.now());
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
