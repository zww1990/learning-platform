package com.demo.service.impl;

import java.util.Date;
import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.demo.service.JobService;

@Service
public class JobServiceImpl implements JobService {
	private static final Logger log = LoggerFactory.getLogger(JobServiceImpl.class);

	@Override
	public void execute(ShardingContext shardingContext) {
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

	@Resource
	private JavaMailSender mailSender;
}
