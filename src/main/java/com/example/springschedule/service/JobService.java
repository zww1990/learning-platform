package com.example.springschedule.service;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

@DisallowConcurrentExecution
public class JobService extends QuartzJobBean {
	private static final Logger log = LoggerFactory.getLogger(JobService.class);

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		log.info("done!!!");
	}
}
