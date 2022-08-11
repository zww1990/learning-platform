package com.example.springschedule.config;

import javax.annotation.Resource;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.springschedule.config.ApplicationConfig.JobConfig;
import com.example.springschedule.service.GiteeJobService;
import com.example.springschedule.service.JgitJobService;

/**
 * Job Auto Configuration
 * 
 * @author zhang weiwei
 * @since 2022年8月11日,下午12:46:17
 */
@Configuration
public class JobAutoConfiguration {

	@Resource
	private ApplicationConfig appConfig;

	@Bean
	@ConditionalOnProperty(//
			prefix = ApplicationConfig.APP_CONFIG_PREFIX, //
			name = "jgit-job.enabled", //
			havingValue = "true")
	JobDetail jgitJobDetail() {
		JobConfig jobConfig = this.appConfig.getJgitJob();
		return JobBuilder.newJob(JgitJobService.class)//
				.withIdentity(jobConfig.getJobKey())//
				.storeDurably()//
				.build();
	}

	@Bean
	@ConditionalOnProperty(//
			prefix = ApplicationConfig.APP_CONFIG_PREFIX, //
			name = "jgit-job.enabled", //
			havingValue = "true")
	Trigger jgitJobTrigger() {
		JobConfig jobConfig = this.appConfig.getJgitJob();
		return TriggerBuilder.newTrigger()//
				.forJob(this.jgitJobDetail())//
				.withIdentity(jobConfig.getTriggerKey())//
				.withSchedule(CronScheduleBuilder.cronSchedule(jobConfig.getCronExpression()))//
				.build();
	}

	@Bean
	@ConditionalOnProperty(//
			prefix = ApplicationConfig.APP_CONFIG_PREFIX, //
			name = "gitee-job.enabled", //
			havingValue = "true")
	JobDetail giteeJobDetail() {
		JobConfig jobConfig = this.appConfig.getGiteeJob();
		return JobBuilder.newJob(GiteeJobService.class)//
				.withIdentity(jobConfig.getJobKey())//
				.storeDurably()//
				.build();
	}

	@Bean
	@ConditionalOnProperty(//
			prefix = ApplicationConfig.APP_CONFIG_PREFIX, //
			name = "gitee-job.enabled", //
			havingValue = "true")
	Trigger giteeJobTrigger() {
		JobConfig jobConfig = this.appConfig.getGiteeJob();
		return TriggerBuilder.newTrigger()//
				.forJob(this.giteeJobDetail())//
				.withIdentity(jobConfig.getTriggerKey())//
				.withSchedule(CronScheduleBuilder.cronSchedule(jobConfig.getCronExpression()))//
				.build();
	}
}
