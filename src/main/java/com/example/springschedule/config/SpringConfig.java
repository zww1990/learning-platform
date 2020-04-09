package com.example.springschedule.config;

import java.util.Optional;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import com.example.springschedule.service.JobService;

@Configuration
@ComponentScan("com.example.springschedule")
public class SpringConfig {

	@Bean
	public SchedulerFactoryBean quartzScheduler(ApplicationContext context,
			@Autowired(required = false) Trigger[] triggers) {
		SchedulerFactoryBean quartzScheduler = new SchedulerFactoryBean();
		quartzScheduler.setJobFactory(new AutowireCapableBeanJobFactory(context.getAutowireCapableBeanFactory()));
		Optional.ofNullable(triggers).ifPresent(quartzScheduler::setTriggers);
		return quartzScheduler;
	}

	@Bean
	public JobDetailFactoryBean jobDetail() {
		Class<JobService> job = JobService.class;
		JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
		jobDetail.setJobClass(job);
		jobDetail.setName(job.getName());
		return jobDetail;
	}

	@Bean
	public CronTriggerFactoryBean trigger(JobDetail jobDetail) {
		CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
		trigger.setCronExpression("*/10 * * * * ?");
		trigger.setJobDetail(jobDetail);
		trigger.setName(jobDetail.getKey().getName());
		return trigger;
	}

	protected class AutowireCapableBeanJobFactory extends SpringBeanJobFactory {
		private final AutowireCapableBeanFactory beanFactory;

		protected AutowireCapableBeanJobFactory(AutowireCapableBeanFactory beanFactory) {
			this.beanFactory = beanFactory;
		}

		@Override
		protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
			Object jobInstance = super.createJobInstance(bundle);
			this.beanFactory.autowireBean(jobInstance);
			this.beanFactory.initializeBean(jobInstance, null);
			return jobInstance;
		}
	}
}
