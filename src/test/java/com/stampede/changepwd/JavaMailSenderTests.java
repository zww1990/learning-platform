package com.stampede.changepwd;

import java.nio.charset.StandardCharsets;
import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import com.stampede.changepwd.domain.Person;

@SpringBootTest
public class JavaMailSenderTests {
	@Resource
	private JavaMailSender javaMailSender;
	@Resource
	private MailProperties mailProperties;

	@Test
	public void testSendMail() {
		try {
			Person person = new Person();
			person.setMail("zhangweiwei01@5i5j.com");
			person.setGivenName("张维维");
			String url = "<a href=\"http://localhost:8080/\" target=\"_blank\">http://localhost:8080/</a>";
			SimpleMailMessage m = new SimpleMailMessage();
			m.setFrom(String.format("配置中心密码重置（涉及SVN、GIT、JIRA、WIKI）<%s>", this.mailProperties.getUsername()));
			m.setTo(person.getMail());
			m.setSubject("重置您的密码");
			m.setText(String.format("%s 您好，\n\n点击以下链接重置您的密码:\n%s\n\n如果您没有请求修改密码，请忽略该邮件。", person.getGivenName(), url));
			this.javaMailSender.send(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMimeMessage() {
		try {
			Person person = new Person();
			person.setMail("zhangweiwei01@5i5j.com");
			person.setGivenName("张维维");
			String url = "<a href=\"http://localhost:8080/\" target=\"_blank\">http://localhost:8080/</a>";
			MimeMessage message = this.javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());
			helper.setFrom(String.format("配置中心密码重置（涉及SVN、GIT、JIRA、WIKI）<%s>", this.mailProperties.getUsername()));
			helper.setTo(person.getMail());
			helper.setSubject("重置您的密码");
			helper.setText(
					String.format("%s 您好，\n\n点击以下链接重置您的密码:\n%s\n\n如果您没有请求修改密码，请忽略该邮件。", person.getGivenName(), url),
					true);
			helper.setPriority(1);
			this.javaMailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
