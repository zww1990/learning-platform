package com.example.springschedule.config;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
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
		quartzScheduler.setJobFactory(new AutowireCapableBeanJobFactory(context.getAutowireCapableBeanFactory()));
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
