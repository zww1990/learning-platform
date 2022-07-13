package com.example.springschedule.config;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import com.example.springschedule.service.JobService;

@Configuration
@ComponentScan("com.example.springschedule")
@PropertySource("classpath:/application.properties")
public class SpringConfig {

	@Bean
	public SchedulerFactoryBean quartzScheduler(ApplicationContext context, Trigger[] triggers) {
		SchedulerFactoryBean quartzScheduler = new SchedulerFactoryBean();
		SpringBeanJobFactory jobFactory = new SpringBeanJobFactory();
		jobFactory.setApplicationContext(context);
		quartzScheduler.setJobFactory(jobFactory);
		quartzScheduler.setTriggers(triggers);
		return quartzScheduler;
	}

	@Bean
	public JobDetailFactoryBean jobDetail() {
		JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
		jobDetail.setJobClass(JobService.class);
		return jobDetail;
	}

	@Bean
	public CronTriggerFactoryBean trigger(JobDetail jobDetail, @Value("${cron.expression}") String cronExpression) {
		CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
		trigger.setCronExpression(cronExpression);
		trigger.setJobDetail(jobDetail);
		return trigger;
	}
}
