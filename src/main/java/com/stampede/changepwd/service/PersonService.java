package com.stampede.changepwd.service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.stampede.changepwd.domain.Person;
import com.stampede.changepwd.domain.PersonParam;
import com.stampede.changepwd.repository.PersonRepository;
import com.stampede.changepwd.util.LdapPasswordUtils;

/**
 * @author ZhangWeiWei
 * @date 2020年2月17日,下午1:36:20
 * @description 人员服务实现类
 */
@Service
public class PersonService {
	@Resource
	private PersonRepository personRepository;
	@Resource
	private LdapTemplate ldapTemplate;
	@Resource
	private JavaMailSender javaMailSender;
	@Resource
	private MailProperties mailProperties;

	/**
	 * @author ZhangWeiWei
	 * @date 2020年2月17日,下午1:36:44
	 * @param param 用户密码参数类
	 * @return 验证用户名密码是否正确
	 */
	public boolean checkUserPassword(PersonParam param) {
		AndFilter filter = new AndFilter();
		filter.and(new EqualsFilter("objectclass", "person"));
		filter.and(new EqualsFilter("uid", param.getUsername()));
		return this.ldapTemplate.authenticate("", filter.encode(), param.getPassword());
	}

	/**
	 * 更新用户密码，并向用户发送邮件。
	 * @author ZhangWeiWei
	 * @date 2020年2月17日,下午7:15:18
	 * @param param 用户密码参数类
	 */
	public void updateUserPassword(PersonParam param) {
		this.personRepository.findOne(LdapQueryBuilder.query().where("uid").is(param.getUsername())).ifPresent(p -> {
			p.setUserPassword(LdapPasswordUtils.md5Password(param.getNewpassword()));
			this.personRepository.save(p);
			MimeMessage message = this.javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());
			try {
				helper.setFrom(String.format("配置中心密码重置（涉及SVN、GIT、JIRA、WIKI）<%s>", this.mailProperties.getUsername()));
				helper.setTo(p.getMail());
				helper.setSubject("您的密码已修改");
				helper.setText(String.format("%s 您好，<br><br>您的密码已修改。<br><br>如果您没有修改密码，请立即联系您的管理员。", p.getGivenName()),
						true);
				helper.setPriority(1);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			this.javaMailSender.send(message);
		});
	}

	/**
	 * @author ZhangWeiWei
	 * @date 2020年2月18日,上午9:48:44
	 * @param param 用户密码参数类
	 * @return 按用户名查询
	 */
	public Optional<Person> findByUsername(PersonParam param) {
		return this.personRepository.findOne(LdapQueryBuilder.query().where("uid").is(param.getUsername()));
	}

	/**
	 * 发送邮件
	 * @author ZhangWeiWei
	 * @date 2020年2月18日,上午10:25:25
	 * @param person 人员数据模型
	 * @param webPath web应用访问路径
	 */
	public void sendMail(Person person, String webPath) {
		int min = 5;
		String url = String.format("%s?token=%s", webPath,
				LdapPasswordUtils.jwtEncode(person.getUid(), min * 60 * 1000));
		MimeMessage message = this.javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());
		try {
			helper.setFrom(String.format("配置中心密码重置（涉及SVN、GIT、JIRA、WIKI）<%s>", this.mailProperties.getUsername()));
			helper.setTo(person.getMail());
			helper.setSubject("重置您的密码");
			helper.setText(String.format(
					"%s 您好，<br><br>点击以下链接重置您的密码，该链接%s分钟后失效：<br><br><a href=\"%s\" target=\"_blank\">%s</a><br><br>如果您没有请求修改密码，请忽略该邮件。",
					person.getGivenName(), min, url, url), true);
			helper.setPriority(1);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		this.javaMailSender.send(message);
	}
}
