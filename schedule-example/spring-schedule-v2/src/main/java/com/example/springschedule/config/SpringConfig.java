package com.example.springschedule.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.springschedule.service.JobService;

@Configuration
public class SpringConfig {

	@Bean
	JobDetail jobDetail() {
		return JobBuilder//
				.newJob(JobService.class)//
				.withIdentity("job_1")//
				.storeDurably()//
				.build();
	}

	@Bean
	Trigger jobTrigger(SpringProperties properties) {
		return TriggerBuilder//
				.newTrigger()//
				.forJob(this.jobDetail())//
				.withIdentity("job_trigger_1")//
				.withSchedule(CronScheduleBuilder.cronSchedule(properties.getExpression()))//
				.build();
	}
}
