package com.stampede.changepwd;

import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
public class JavaMailSenderTests {
	@Resource
	private JavaMailSender javaMailSender;
	@Resource
	private MailProperties mailProperties;

	@Test
	public void testSendMail() {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(this.mailProperties.getUsername());
			message.setTo("zhangweiwei01@5i5j.com");
			message.setSubject("主题：简单邮件（采用加密传输方式）");
			message.setText("测试邮件内容（采用加密传输方式）");
			this.javaMailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
