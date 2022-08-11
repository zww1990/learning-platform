package com.example.springschedule;

import javax.annotation.Resource;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.springschedule.config.ApplicationConfig;
import com.example.springschedule.config.ApplicationConfig.JobConfig;
import com.example.springschedule.service.JgitJobService;

import lombok.extern.slf4j.Slf4j;

/**
 * Spring Schedule Application
 * 
 * @author zhang weiwei
 * @since 2022年8月5日,下午8:17:32
 */
@SpringBootApplication(exclude = { ProjectInfoAutoConfiguration.class })
@Slf4j
public class SpringScheduleApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringScheduleApplication.class, args);
		log.info("应用程序上下文Bean定义计数={}", context.getBeanDefinitionCount());
	}

	@Resource
	private ApplicationConfig appConfig;

	@Bean
	@ConditionalOnProperty(prefix = "app", name = "jgit-job.enabled", havingValue = "true")
	JobDetail jobDetail() {
		JobConfig jobConfig = this.appConfig.getJgitJob();
		return JobBuilder.newJob(JgitJobService.class).withIdentity(jobConfig.getJobKey()).storeDurably().build();
	}

	@Bean
	@ConditionalOnProperty(prefix = "app", name = "jgit-job.enabled", havingValue = "true")
	Trigger jobTrigger() {
		JobConfig jobConfig = this.appConfig.getJgitJob();
		return TriggerBuilder.newTrigger().forJob(this.jobDetail()).withIdentity(jobConfig.getTriggerKey())
				.withSchedule(CronScheduleBuilder.cronSchedule(jobConfig.getCronExpression())).build();
	}
}
