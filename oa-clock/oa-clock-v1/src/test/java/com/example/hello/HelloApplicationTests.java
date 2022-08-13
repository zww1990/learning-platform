package com.example.hello;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.example.hello.config.ApplicationProperties;

/**
 * HelloApplicationTests
 * 
 * @author zhang weiwei
 * @since 2022年8月12日,下午9:17:03
 */
@SpringBootTest
public class HelloApplicationTests {
	@Resource
	private ApplicationContext context;
	@Resource
	private JavaMailSender mailSender;
	@Resource
	private MailProperties mailProperties;
	@Resource
	private ApplicationProperties appProperties;

	@Test
	public void testContextLoads() {
		try {
			System.err.println(this.context.getBeanDefinitionCount());
			System.err.println(this.context.getBean(JavaMailSender.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSendMail() {
		try {
			SimpleMailMessage m = new SimpleMailMessage();
			m.setFrom(String.format("test <%s>", this.mailProperties.getUsername()));
			m.setTo(this.mailProperties.getUsername());
			m.setSubject("test");
			m.setText("this is test mail");
			this.mailSender.send(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
