package com.example.hello;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.EmbeddedValueResolver;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.StringValueResolver;
import org.springframework.web.context.support.GenericWebApplicationContext;

import com.example.hello.event.MyEvent;

/**
 * HelloApplicationTests
 * 
 * @author zhang weiwei
 * @since 2022年8月12日,下午9:17:03
 */
@SpringBootTest
@EnableAsync
public class HelloApplicationTests {
	@Autowired
	private ApplicationContext context;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private MailProperties mailProperties;

	@Test
	public void testContextLoads() {
		try {
			System.err.println(this.context.getBeanDefinitionCount());
			System.err.println(this.context.getBean(JavaMailSender.class));
			StringValueResolver resolver = new EmbeddedValueResolver(
					((GenericWebApplicationContext) this.context).getBeanFactory());
			System.err.println(resolver.resolveStringValue("${app.task.cron}"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSendMail() {
		try {
			SimpleMailMessage m = new SimpleMailMessage();
			m.setFrom(this.mailProperties.getUsername());
			m.setTo(this.mailProperties.getUsername());
			m.setSubject("test");
			m.setText("this is test mail");
			this.mailSender.send(m);
			System.err.println("done.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testPublishEvent() {
		try {
			String msg = "hello world";
			System.err.println("发布消息：" + msg);
			ApplicationEvent event = new MyEvent(this, msg);
			this.context.publishEvent(event);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
