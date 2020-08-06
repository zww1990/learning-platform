package com.stampede.changepwd.service;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import com.stampede.changepwd.ChangepwdProperties;
import com.stampede.changepwd.ChangepwdProperties.PersonJob;

/**
 * @author ZhangWeiWei
 * @date 2020-8-6,13:42:13
 * @description 针对控股入职的员工，创建LDAP账号，并发送邮件通知。
 */
public class PersonJobService {
	private static final Logger log = LoggerFactory.getLogger(PersonJobService.class);
	@Resource
	private ChangepwdProperties properties;
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Scheduled(cron = "${changepwd.job.cron}", zone = "${spring.jackson.time-zone}")
	public void createLdapAccountAndSendMail() {
		LocalDateTime now = LocalDateTime.now();
		PersonJob job = this.properties.getJob();
		String hhmmss = now.format(DateTimeFormatter.ofPattern(job.getPattern()));
		String yymmdd = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		log.info("{} {}", yymmdd, hhmmss);
		try {
			InetAddress ip4 = Inet4Address.getLocalHost();
			System.err.println("------------------");
			System.err.println(ip4.getHostAddress());
			System.err.println("------------------");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		// 查询控股公司、主职、有效、入职、存在公司邮箱、当天的员工记录。
		String sql = "SELECT to_char(u.user_id) AS user_id, u.user_name, u.comp_email FROM bdm_hr_user_job j INNER JOIN bdm_hr_user u ON j.user_id = u.user_id AND j.comp_id = u.comp_id WHERE j.comp_id = 26 AND j.post_rcd = 0 AND j.status = 1 AND j.action = 'HIR' AND u.comp_email IS NOT NULL AND j.create_time BETWEEN ? AND ?";
		job.getTimes().stream().forEach(time -> {
			if (time.getEnd().equals(hhmmss)) {
				String begin = String.format("%s %s", yymmdd, time.getBegin());
				String end = String.format("%s %s", yymmdd, time.getEnd());
				log.info("开始时间={}, 结束时间={}", begin, end);
				List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql, begin, end);
				System.err.println(list.size());
			}
		});
	}
}
