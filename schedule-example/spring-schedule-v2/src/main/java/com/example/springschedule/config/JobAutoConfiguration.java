package com.example.springschedule.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.example.springschedule.config.ApplicationConfig.JobConfig;
import com.example.springschedule.service.exchange.GiteeHttpExchange;
import com.example.springschedule.service.job.GiteeJobService;
import com.example.springschedule.service.job.JgitJobService;

/**
 * Job Auto Configuration
 * 
 * @author zhang weiwei
 * @since 2022年8月11日,下午12:46:17
 */
@Configuration
public class JobAutoConfiguration {
	@Autowired
	private ApplicationConfig appConfig;

	@Bean
	GiteeHttpExchange giteeHttpExchange() {
		HttpServiceProxyFactory factory = HttpServiceProxyFactory
				.builder(WebClientAdapter.forClient(WebClient.create())).build();
		return factory.createClient(GiteeHttpExchange.class);
	}

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
