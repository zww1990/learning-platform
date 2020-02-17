package com.stampede.changepwd.service;

import javax.annotation.Resource;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
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
			SimpleMailMessage m = new SimpleMailMessage();
			m.setFrom(String.format("配置中心密码重置（涉及SVN、GIT、JIRA、WIKI）<%s>", this.mailProperties.getUsername()));
			m.setTo(p.getMail());
			m.setSubject("您的密码已修改");
			m.setText(String.format("%s 您好，\n\n您的密码已修改。\n\n如果您没有修改密码，请立即联系您的管理员。", p.getGivenName()));
			this.javaMailSender.send(m);
		});
	}
}
