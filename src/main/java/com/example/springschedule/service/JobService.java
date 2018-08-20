package com.example.springschedule.service;

import java.util.Date;
import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.quartz.QuartzJobBean;

@DisallowConcurrentExecution
public class JobService extends QuartzJobBean {
	private static final Logger log = LoggerFactory.getLogger(JobService.class);
	@Resource
	private JavaMailSender mailSender;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JavaMailSenderImpl jms = (JavaMailSenderImpl) this.mailSender;
		MimeMessage message = jms.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setFrom(jms.getUsername());
			helper.setTo("1160133573@qq.com");
			helper.setSubject("这是一段隐藏的内容");
			helper.setText("<h1 style=\"color: white;\">Fucking you all day and all night!!!</h1>", true);
			helper.setSentDate(new Date());
			jms.send(message);
			log.info("done!!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
