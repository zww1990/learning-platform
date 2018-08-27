package com.example.springschedule;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import com.example.springschedule.service.JobService;

@SpringBootApplication
public class SpringScheduleApplication {
	private static final Logger log = LoggerFactory.getLogger(SpringScheduleApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringScheduleApplication.class, args);
		log.info("应用程序上下文Bean定义计数={}", context.getBeanDefinitionCount());
	}

	@Bean
	public JobDetail jobDetail() {
		Class<JobService> job = JobService.class;
		return JobBuilder.newJob(job).withIdentity(job.getName()).storeDurably().build();
	}

	@Bean
	public Trigger trigger(JobDetail jobDetail) {
		return TriggerBuilder.newTrigger().forJob(jobDetail).withIdentity(jobDetail.getKey().getName())
				.withSchedule(CronScheduleBuilder.cronSchedule("*/30 * * * * ?")).build();
	}
}
