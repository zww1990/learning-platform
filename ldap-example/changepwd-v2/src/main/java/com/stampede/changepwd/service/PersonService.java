package com.stampede.changepwd.service;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.naming.Name;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;

import org.gitlab4j.api.GitLabApi;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.atlassian.httpclient.api.Response;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.auth.BasicHttpAuthenticationHandler;
import com.atlassian.jira.rest.client.internal.async.AsynchronousHttpClientFactory;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.jira.rest.client.internal.async.DisposableHttpClient;
import com.stampede.changepwd.ChangepwdProperties;
import com.stampede.changepwd.domain.Person;
import com.stampede.changepwd.domain.PersonParam;
import com.stampede.changepwd.repository.PersonRepository;
import com.stampede.changepwd.util.LdapPasswordUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ZhangWeiWei
 * @date 2020年2月17日,下午1:36:20
 * @description 人员服务实现类
 */
@Service
@Slf4j
public class PersonService {
	@Resource
	private PersonRepository personRepository;
	@Resource
	private LdapTemplate ldapTemplate;
	@Resource
	private JavaMailSender javaMailSender;
	@Resource
	private ChangepwdProperties properties;
	@Resource
	private JdbcTemplate jdbcTemplate;

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
	 * 
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
				helper.setFrom(this.properties.getUpdate().getFrom());
				helper.setTo(p.getMail());
				helper.setSubject(this.properties.getUpdate().getSubject());
				helper.setText(String.format(this.properties.getUpdate().getText(), p.getGivenName()), true);
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
	public Optional<Person> findByUsername(String param) {
		return this.personRepository.findOne(LdapQueryBuilder.query().where("uid").is(param));
	}

	/**
	 * @author ZhangWeiWei
	 * @date 2020-8-10,14:06:57
	 * @param param 人员编号
	 * @return 按人员编号查询
	 */
	public Optional<Person> findByUidNumber(String param) {
		return this.personRepository.findOne(LdapQueryBuilder.query().where("uidNumber").is(param));
	}

	/**
	 * 删除人员数据
	 * 
	 * @author ZhangWeiWei
	 * @date 2020-8-10,14:07:32
	 * @param p 人员数据模型
	 */
	public void delete(Person p) {
		this.personRepository.delete(p);
	}

	/**
	 * 发送邮件
	 * 
	 * @author ZhangWeiWei
	 * @date 2020年2月18日,上午10:25:25
	 * @param person  人员数据模型
	 * @param webPath web应用访问路径
	 */
	public void sendMail(Person person, String webPath) {
		String url = String.format("%s/person/resetpage?token=%s", webPath,
				LdapPasswordUtils.jwtEncode(person.getUid(), this.properties.getReset().getExpiration()));
		MimeMessage message = this.javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());
		try {
			helper.setFrom(this.properties.getReset().getFrom());
			helper.setTo(person.getMail());
			helper.setSubject(this.properties.getReset().getSubject());
			helper.setText(String.format(this.properties.getReset().getText(), person.getGivenName(), url, url), true);
			helper.setPriority(1);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		this.javaMailSender.send(message);
	}

	/**
	 * 管理员发送邮件
	 * 
	 * @author ZhangWeiWei
	 * @date 2020年2月27日,上午10:04:57
	 * @param person  人员数据模型
	 * @param webPath web应用访问路径
	 */
	public void sendMailForAdmin(Person person, String webPath) {
		Name dn = LdapUtils.newLdapName(String.format("uid=%s,ou=People", person.getUid()));
		BasicAttribute ba = new BasicAttribute("objectClass");
		ba.add("inetOrgPerson");
		ba.add("posixAccount");
		ba.add("top");
		Attributes attr = new BasicAttributes();
		attr.put(ba);
		attr.put("cn", person.getUid());
		attr.put("sn", person.getUid());
		attr.put("uid", person.getUid());
		attr.put("mail", person.getMail());
		attr.put("givenName", person.getGivenName());
		attr.put("uidNumber", person.getUidNumber());
		attr.put("gidNumber", person.getGidNumber());
		attr.put("homeDirectory", person.getHomeDirectory());
		attr.put("userPassword", this.properties.getDefaultPassword());
		// 存储到ldap中
		this.ldapTemplate.bind(dn, null, attr);

		// 登录jira进行账户激活
		this.activateJiraAccount(person);
		// 登录gitlab进行账户激活
		this.activateGitlabAccount(person);

		String url = String.format("%s/person/resetpage?token=%s", webPath,
				LdapPasswordUtils.jwtEncode(person.getUid(), this.properties.getCreate().getExpiration()));
		MimeMessage message = this.javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());
		try {
			helper.setFrom(this.properties.getCreate().getFrom());
			helper.setTo(person.getMail());
			helper.setSubject(this.properties.getCreate().getSubject());
			helper.setText(String.format(this.properties.getCreate().getText(), person.getGivenName(), person.getUid(),
					url, url), true);
			helper.setPriority(1);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		this.javaMailSender.send(message);
	}

	/**
	 * 登录jira进行账户激活
	 * 
	 * @param person {@link Person}
	 */
	private void activateJiraAccount(Person person) {
		// 在登录jira之前，需要进行ldap同步到jira中
		DisposableHttpClient dhClient = null;
		try {
			dhClient = new AsynchronousHttpClientFactory().createClient(URI.create(this.properties.getJiraUrl()),
					new BasicHttpAuthenticationHandler(this.properties.getJiraAdminUsername(),
							this.properties.getJiraAdminPassword()));
			Response response = dhClient.newRequest(this.properties.getJiraSyncUrl()).get().get();
			if (response.isError()) {
				log.error("用户目录[ldap ==>> jira]同步失败 >>> {}", response.getStatusText());
				return;
			}
			log.info("用户目录[ldap ==>> jira]同步成功 >>> {}", response.getStatusText());
		} catch (Exception e) {
			log.error("用户目录[ldap ==>> jira]同步失败", e);
		} finally {
			Optional.ofNullable(dhClient).ifPresent(c -> {
				try {
					c.destroy();
				} catch (Exception e) {
					log.error("对象[dhClient]不能正常释放资源", e);
				}
			});
		}
		// 账号同步之后，再进行登录激活
		try (JiraRestClient jrClient = new AsynchronousJiraRestClientFactory().createWithBasicHttpAuthentication(
				URI.create(this.properties.getJiraUrl()), person.getUid(), this.properties.getDefaultPassword())) {
			log.info("成功激活jira账号>>>{}", jrClient.getUserClient().getUser(person.getUid()).get());
		} catch (Exception e) {
			log.error("激活jira账号失败", e);
		}
	}

	/**
	 * 登录gitlab进行账户激活
	 * 
	 * @param person {@link Person}
	 */
	private void activateGitlabAccount(Person person) {
		try (GitLabApi api = GitLabApi.oauth2Login(this.properties.getGitlabUrl(), person.getUid(),
				this.properties.getDefaultPassword())) {
			Optional.ofNullable(api).map(GitLabApi::getUserApi).map(m -> {
				try {
					return m.getCurrentUser();
				} catch (Exception e) {
					log.error("激活gitlab账号失败", e);
					return null;
				}
			}).ifPresent(c -> {
				log.info("成功激活gitlab账号>>>{}", c);
			});
		} catch (Exception e) {
			log.error("激活gitlab账号失败", e);
		}
	}

	/**
	 * @author ZhangWeiWei
	 * @date 2020-8-10,14:05:41
	 * @param userId 员工编号
	 * @return 按员工编号查询员工姓名、公司邮箱
	 */
	public Map<String, Object> queryForUserMap(String userId) {
		try {
			String sql = "SELECT user_name, comp_email FROM bdm_user WHERE user_id = ? and comp_email is not null";
			return this.jdbcTemplate.queryForMap(sql, userId);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			return Collections.emptyMap();
		}
	}

	/**
	 * @author ZhangWeiWei
	 * @date 2020-8-10,14:05:13
	 * @return 查询外包人员可用编号
	 */
	public long queryWaibaoId() {
		AndFilter filter = new AndFilter();
		filter.and(new EqualsFilter("objectclass", "person"));
		return this.ldapTemplate
				.search("", filter.encode(), (Attributes attributes) -> attributes.get("uidnumber").get().toString())
				.stream().filter(p -> p.startsWith("100")).mapToLong(Long::parseLong).max()
				.orElse(System.currentTimeMillis()) + 1;
	}
}
