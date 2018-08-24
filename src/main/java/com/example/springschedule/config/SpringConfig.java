package com.example.springschedule.config;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import com.example.springschedule.service.JobService;

@Configuration
@ComponentScan("com.example.springschedule")
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
	public SchedulerFactoryBean quartzScheduler(ApplicationContext context, Trigger[] triggers) {
		SchedulerFactoryBean quartzScheduler = new SchedulerFactoryBean();
		quartzScheduler.setJobFactory(new AutowireCapableBeanJobFactory(context.getAutowireCapableBeanFactory()));
		quartzScheduler.setTriggers(triggers);
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
