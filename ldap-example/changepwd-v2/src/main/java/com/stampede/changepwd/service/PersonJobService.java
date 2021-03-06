package com.stampede.changepwd.service;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import com.stampede.changepwd.ChangepwdProperties;
import com.stampede.changepwd.ChangepwdProperties.PersonJob;
import com.stampede.changepwd.domain.Person;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ZhangWeiWei
 * @date 2020-8-6,13:42:13
 * @description 针对控股入职的员工，创建LDAP账号，并发送邮件通知。
 */
@Slf4j
public class PersonJobService {
	@Resource
	private ChangepwdProperties properties;
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private PersonService personService;
	@Resource
	private ServerProperties server;

	public PersonJobService() {
		super();
		log.info("<<<自动创建LDAP账号定时任务已注册>>>");
	}

	@Scheduled(cron = "${changepwd.third.cron}", zone = "${spring.jackson.time-zone}")
	public void thirdCreateLdapAccountAndSendMail() {
		String yymmdd = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		this.createLdapAccountAndSendMail(yymmdd, this.properties.getThird());
		this.delete(yymmdd, this.properties.getThird());
	}

	private void createLdapAccountAndSendMail(String yymmdd, PersonJob job) {
		String webPath = String.format("http://%s:%s%s", getHostAddress(), this.server.getPort(),
				this.server.getServlet().getContextPath());
		// 查询控股公司、主职、有效、入职、存在公司邮箱、当天的员工记录。
		String sql = "SELECT to_char(u.user_id) AS user_id, u.user_name, u.comp_email FROM bdm_hr_user_job j INNER JOIN bdm_hr_user u ON j.user_id = u.user_id AND j.comp_id = u.comp_id WHERE j.comp_id = 26 AND j.post_rcd = 0 AND j.status = 1 AND j.action = 'HIR' AND u.comp_email IS NOT NULL AND j.create_time BETWEEN to_timestamp(?, 'yyyy-mm-dd hh24:mi:ss') AND to_timestamp(?, 'yyyy-mm-dd hh24:mi:ss')";
		String begin = String.format("%s %s", yymmdd, job.getBegin());
		String end = String.format("%s %s", yymmdd, job.getEnd());
		log.info("开始时间={}, 结束时间={}", begin, end);
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql, begin, end);
		if (list.isEmpty()) {
			log.info("没有查询到符合条件的记录。");
			return;
		}
		list.forEach(map -> {
			log.info("{}", map);
			String uidNumber = map.get("USER_ID").toString();
			String givenName = map.get("USER_NAME").toString();
			String mail = map.get("COMP_EMAIL").toString();
			String uid = mail.substring(0, mail.indexOf("@"));
			if (this.personService.findByUsername(uid).isPresent()) {
				log.warn("该uid={}已存在！", uid);
			} else if (this.personService.findByUidNumber(uidNumber).isPresent()) {
				log.warn("该uidNumber={}已存在！", uidNumber);
			} else {
				Person p = new Person()//
						.setUid(uid)//
						.setGivenName(givenName)//
						.setGidNumber("501")//
						.setUidNumber(uidNumber)//
						.setMail(mail);
				this.personService.sendMailForAdmin(p, webPath);
				log.info("定时任务>>>账号[{}]已创建，邮件已发至邮箱[{}]", uid, mail);
			}
		});
	}

	private void delete(String yymmdd, PersonJob job) {
		String begin = String.format("%s %s", yymmdd, job.getBegin());
		String end = String.format("%s %s", yymmdd, job.getEnd());
		log.info("开始时间={}, 结束时间={}", begin, end);
		// 查询控股公司、主职、无效、离职、当天的员工记录。
		String sql = "SELECT to_char(u.user_id) AS user_id FROM bdm_hr_user_job j INNER JOIN bdm_hr_user u ON j.user_id = u.user_id AND j.comp_id = u.comp_id WHERE j.comp_id = 26 AND j.post_rcd = 0 AND j.status = 0 AND j.action = 'TER' AND j.create_time BETWEEN to_timestamp(?, 'yyyy-mm-dd hh24:mi:ss') AND to_timestamp(?, 'yyyy-mm-dd hh24:mi:ss')";
		List<String> list = this.jdbcTemplate.queryForList(sql, String.class, begin, end);
		if (list.isEmpty()) {
			log.info("没有查询到符合条件的记录。");
			return;
		}
		list.forEach(uid -> this.personService.findByUidNumber(uid).ifPresent(p -> {
			this.personService.delete(p);
			log.info("员工[{}]已离职，LDAP账号[{}]已删除", p.getUidNumber(), p.getUid());
		}));
	}

	/**
	 * @author ZhangWeiWei
	 * @date 2020-8-10,14:09:48
	 * @return 获取机器所在网络中的IP
	 */
	private static String getHostAddress() {
		try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();// 获取本地所有网络接口
			while (networkInterfaces.hasMoreElements()) {// 遍历枚举中的每一个元素
				Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
				while (inetAddresses.hasMoreElements()) {
					InetAddress ia = inetAddresses.nextElement();
					if (!ia.isLoopbackAddress() && !ia.isLinkLocalAddress() && ia.isSiteLocalAddress()) {
						return ia.getHostAddress();
					}
				}
			}
			return "<unknown>";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
