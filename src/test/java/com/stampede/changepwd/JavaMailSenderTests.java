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
			message.setFrom(String.format("配置中心密码重置（涉及SVN、GIT、JIRA、WIKI）<%s>", this.mailProperties.getUsername()));
			message.setTo("zhangweiwei01@5i5j.com");
			message.setSubject("您的密码已修改");
			message.setText(String.format("%s 您好，\n\n您的密码已修改。\n\n如果您没有修改密码，请立即联系您的管理员。", "张维维"));
			this.javaMailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
