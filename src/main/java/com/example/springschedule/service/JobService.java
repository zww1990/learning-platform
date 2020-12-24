package com.example.springschedule.service;

import java.time.LocalDateTime;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobService implements Job {
	private static final Logger log = LoggerFactory.getLogger(JobService.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			log.info("{} -- {}", context.getScheduler().getSchedulerInstanceId(), LocalDateTime.now());
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
