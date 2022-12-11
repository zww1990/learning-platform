package com.example.springschedule.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.example.springschedule.config.ApplicationConfig.JobConfig;
import com.example.springschedule.service.exchange.GiteeHttpExchange;
import com.example.springschedule.service.job.GiteeJobService;
import com.example.springschedule.service.job.GithubJobService;
import com.example.springschedule.service.job.JgitJobService;

/**
 * Job Auto Configuration
 * 
 * @author zhang weiwei
 * @since 2022年8月11日,下午12:46:17
 */
@Configuration
public class JobAutoConfiguration {

	@Bean
	GiteeHttpExchange giteeHttpExchange() {
		HttpServiceProxyFactory factory = HttpServiceProxyFactory
				.builder(WebClientAdapter.forClient(WebClient.create())).build();
		return factory.createClient(GiteeHttpExchange.class);
	}

	@Configuration
	@ConditionalOnProperty(//
			prefix = ApplicationConfig.APP_CONFIG_PREFIX, //
			name = "jgit-job.enabled", //
			havingValue = "true")
	public static class JgitJobAutoConfiguration {
		@Bean
		JobDetail jgitJobDetail(ApplicationConfig appConfig) {
			return JobBuilder.newJob(JgitJobService.class)//
					.withIdentity(appConfig.getJgitJob().getJobKey())//
					.storeDurably()//
					.build();
		}

		@Bean
		Trigger jgitJobTrigger(ApplicationConfig appConfig, JobDetail jgitJobDetail) {
			JobConfig jc = appConfig.getJgitJob();
			return TriggerBuilder.newTrigger()//
					.forJob(jgitJobDetail)//
					.withIdentity(jc.getTriggerKey())//
					.withSchedule(CronScheduleBuilder.cronSchedule(jc.getCronExpression()))//
					.build();
		}
	}

	@Configuration
	@ConditionalOnProperty(//
			prefix = ApplicationConfig.APP_CONFIG_PREFIX, //
			name = "gitee-job.enabled", //
			havingValue = "true")
	public static class GiteeJobAutoConfiguration {
		@Bean
		JobDetail giteeJobDetail(ApplicationConfig appConfig) {
			return JobBuilder.newJob(GiteeJobService.class)//
					.withIdentity(appConfig.getGiteeJob().getJobKey())//
					.storeDurably()//
					.build();
		}

		@Bean
		Trigger giteeJobTrigger(ApplicationConfig appConfig, JobDetail giteeJobDetail) {
			JobConfig jc = appConfig.getGiteeJob();
			return TriggerBuilder.newTrigger()//
					.forJob(giteeJobDetail)//
					.withIdentity(jc.getTriggerKey())//
					.withSchedule(CronScheduleBuilder.cronSchedule(jc.getCronExpression()))//
					.build();
		}
	}

	@Configuration
	@ConditionalOnProperty(//
			prefix = ApplicationConfig.APP_CONFIG_PREFIX, //
			name = "github-job.enabled", //
			havingValue = "true")
	public static class GithubJobAutoConfiguration {
		@Bean
		JobDetail githubJobDetail(ApplicationConfig appConfig) {
			return JobBuilder.newJob(GithubJobService.class)//
					.withIdentity(appConfig.getGithubJob().getJobKey())//
					.storeDurably()//
					.build();
		}

		@Bean
		Trigger githubTrigger(ApplicationConfig appConfig, JobDetail githubJobDetail) {
			JobConfig jc = appConfig.getGithubJob();
			return TriggerBuilder.newTrigger()//
					.forJob(githubJobDetail)//
					.withIdentity(jc.getTriggerKey())//
					.withSchedule(CronScheduleBuilder.cronSchedule(jc.getCronExpression()))//
					.build();
		}
	}
}
