package com.example.hello.config;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import com.example.hello.service.job.HelloJob;

/**
 * HelloAutoConfiguration
 * 
 * @author zhang weiwei
 * @since 2022年8月12日,下午9:38:58
 */
@Configuration
public class HelloAutoConfiguration {

	@Bean
	RestTemplate restTemplate(RestTemplateBuilder restBuilder) {
		return restBuilder.build();
	}

	@Bean
	JobDetail jobDetail(ApplicationConfig appConfig) {
		return JobBuilder.newJob(HelloJob.class)//
				.withIdentity(appConfig.getJobConfig().getJobKey())//
				.storeDurably()//
				.build();
	}

	@Bean
	ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}
}
