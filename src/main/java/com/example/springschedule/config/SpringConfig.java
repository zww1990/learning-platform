package com.example.springschedule.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import com.example.springschedule.service.JobService;

@Configuration
public class SpringConfig {
	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl ms = new JavaMailSenderImpl();
		ms.setDefaultEncoding("UTF-8");
		ms.setProtocol("smtp");
		ms.setHost("smtp.sohu.com");
		ms.setPort(25);
		ms.setUsername("nokia0561861@sohu.com");
		ms.setPassword("zhangWW@1021");
		return ms;
	}

	@Bean
	public SchedulerFactoryBean quartzScheduler() {
		return new SchedulerFactoryBean();
	}

	@Bean
	public JobDetail jobDetail() {
		Class<JobService> job = JobService.class;
		return JobBuilder.newJob(job).withIdentity(job.getName()).storeDurably().build();
	}

	@Bean
	public Trigger trigger(JobDetail jobDetail) {
		return TriggerBuilder.newTrigger().forJob(jobDetail).withIdentity(JobService.class.getName())
				.withSchedule(CronScheduleBuilder.cronSchedule("*/10 * * * * ?")).build();
	}
}
