package com.example.hello.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.example.hello.model.ApplicationProperties;
import com.example.hello.model.ApplicationProperties.Address;
import com.example.hello.model.ApplicationProperties.JobConfig;
import com.example.hello.model.ApplicationProperties.UserInfo;
import com.example.hello.model.JobInfo;
import com.example.hello.model.ResponseBody;
import com.example.hello.model.UserLogin;
import com.example.hello.service.HelloService;

import cn.net.yzl.oa.entity.ClockWorkStatus;
import cn.net.yzl.oa.entity.SqlExecQueryDTO;
import cn.net.yzl.oa.entity.vo.AppStaffClockVO;
import cn.net.yzl.oa.util.AESUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * HelloServiceImpl
 * 
 * @author zhang weiwei
 * @since 2022年8月6日,下午4:17:06
 */
@Service
@Slf4j
@SuppressWarnings("unchecked")
public class HelloServiceImpl implements HelloService {
	@Resource
	private RestTemplate restTemplate;
	@Resource
	private ApplicationProperties properties;
	@Resource
	private Scheduler scheduler;
	@Resource
	private JobDetail jobDetail;

	@Override
	public ResponseBody<List<UserInfo>> getUsers() {
		log.info("从当前应用中加载配置成功");
		return new ResponseBody<List<UserInfo>>()//
				.setCode(HttpStatus.OK.value())//
				.setStatus(ResponseBody.SUCCESS)//
				.setData(this.properties.getUsers());
	}

	@Override
	public ResponseBody<List<Address>> getAddresses() {
		log.info("从当前应用中加载配置成功");
		return new ResponseBody<List<Address>>()//
				.setCode(HttpStatus.OK.value())//
				.setStatus(ResponseBody.SUCCESS)//
				.setData(this.properties.getAddresses());
	}

	@Override
	public ResponseBody<?> saveAddress(Address address) {
		if (!StringUtils.hasText(address.getAddress())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(ResponseBody.FAILURE)//
					.setMessage("[address]不能为空");
		}
		if (address.getLatitude() == null) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(ResponseBody.FAILURE)//
					.setMessage("[latitude]不能为空");
		}
		if (address.getLongitude() == null) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(ResponseBody.FAILURE)//
					.setMessage("[longitude]不能为空");
		}
		if (address.getId() == null) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(ResponseBody.FAILURE)//
					.setMessage("[id]不能为空");
		}
		log.info("{}", address);
		this.properties.getAddresses().add(address);
		return new ResponseBody<List<Address>>()//
				.setCode(HttpStatus.OK.value())//
				.setStatus(ResponseBody.SUCCESS);
	}

	@Override
	public ResponseBody<?> initStaffClock(UserLogin userLogin) {
		if (!StringUtils.hasText(userLogin.getUserNo())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(ResponseBody.FAILURE)//
					.setMessage("[userNo]不能为空");
		}
		log.info("{}", userLogin);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("appid", "oa");
		ResponseBody<Map<String, Object>> body = this.restTemplate
				.exchange(this.properties.getInitStaffClockUrl() + userLogin.getUserNo(), HttpMethod.GET,
						new HttpEntity<>(headers), ResponseBody.class)
				.getBody();
		if (!CollectionUtils.isEmpty(body.getData())) {
			body.getData().remove("wifiList");
			body.getData().remove("rangeList");
			body.getData().put("clockWorkOffStatusName",
					ClockWorkStatus.codeToName((Integer) body.getData().get("clockWorkOffStatus")));
			body.getData().put("clockWorkOnStatusName",
					ClockWorkStatus.codeToName((Integer) body.getData().get("clockWorkOnStatus")));
		}
		log.info("{}", body);
		return body;
	}

	@Override
	public ResponseBody<?> userLoginAndStaffClockV2(UserLogin userLogin) {
		if (!StringUtils.hasText(userLogin.getUserNo())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(ResponseBody.FAILURE)//
					.setMessage("[userNo]不能为空");
		}
		if (!StringUtils.hasText(userLogin.getAddress())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(ResponseBody.FAILURE)//
					.setMessage("[address]不能为空");
		}
		if (userLogin.getLatitude() == null) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(ResponseBody.FAILURE)//
					.setMessage("[latitude]不能为空");
		}
		if (userLogin.getLongitude() == null) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(ResponseBody.FAILURE)//
					.setMessage("[longitude]不能为空");
		}
		log.info("补卡>>>{}", userLogin);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("appid", "oa");
		ThreadLocalRandom random = ThreadLocalRandom.current();
		AppStaffClockVO vo = new AppStaffClockVO()//
				.setAddress(userLogin.getAddress())//
				.setLatitude(userLogin.getLatitude().add(BigDecimal.valueOf(random.nextFloat())).setScale(7,
						RoundingMode.HALF_UP))//
				.setLongitude(userLogin.getLongitude().add(BigDecimal.valueOf(random.nextFloat())).setScale(7,
						RoundingMode.HALF_UP))//
				.setStaffNo(userLogin.getUserNo())//
				.setClockTime(Date.from(userLogin.getClockTime().atZone(ZoneId.systemDefault()).toInstant()));
		ResponseBody<?> body = this.restTemplate
				.postForEntity(this.properties.getStaffClockUrl(), new HttpEntity<>(vo, headers), ResponseBody.class)
				.getBody();
		log.info("{}", body);
		String time = userLogin.getClockTime().format(DateTimeFormatter.ofPattern(AESUtil.DATEFORMAT));
		String url = String.format(this.properties.getCreateOaAttendUrl(), userLogin.getUserNo(), time, time);
		body = this.restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), ResponseBody.class).getBody();
		log.info("{} - {}", url, body);
		if (this.properties.getUsers().stream().noneMatch(p -> p.getUserNo().equals(userLogin.getUserNo()))) {
			this.properties.getUsers().add(//
					new UserInfo()//
							.setUserNo(userLogin.getUserNo())//
							.setUsername(userLogin.getUsername()));
		}
		return body;
	}

	@Override
	public ResponseBody<?> userLoginAndStaffClockV1(UserLogin userLogin) {
		if (!StringUtils.hasText(userLogin.getUserNo())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(ResponseBody.FAILURE)//
					.setMessage("[userNo]不能为空");
		}
		if (!StringUtils.hasText(userLogin.getAddress())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(ResponseBody.FAILURE)//
					.setMessage("[address]不能为空");
		}
		if (userLogin.getLatitude() == null) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(ResponseBody.FAILURE)//
					.setMessage("[latitude]不能为空");
		}
		if (userLogin.getLongitude() == null) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(ResponseBody.FAILURE)//
					.setMessage("[longitude]不能为空");
		}
		log.info("打卡>>>{}", userLogin);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("appid", "oa");
		ThreadLocalRandom random = ThreadLocalRandom.current();
		AppStaffClockVO vo = new AppStaffClockVO()//
				.setAddress(userLogin.getAddress())//
				.setLatitude(userLogin.getLatitude().add(BigDecimal.valueOf(random.nextFloat())).setScale(7,
						RoundingMode.HALF_UP))//
				.setLongitude(userLogin.getLongitude().add(BigDecimal.valueOf(random.nextFloat())).setScale(7,
						RoundingMode.HALF_UP))//
				.setStaffNo(userLogin.getUserNo())//
				.setClockTime(new Date());
		ResponseBody<?> body = this.restTemplate
				.postForEntity(this.properties.getStaffClockUrl(), new HttpEntity<>(vo, headers), ResponseBody.class)
				.getBody();
		log.info("{}", body);
		if (this.properties.getUsers().stream().noneMatch(p -> p.getUserNo().equals(userLogin.getUserNo()))) {
			this.properties.getUsers().add(//
					new UserInfo()//
							.setUserNo(userLogin.getUserNo())//
							.setUsername(userLogin.getUsername()));
		}
		return this.initStaffClock(userLogin);
	}

	@Override
	public ResponseBody<?> userLoginAndStaffClockV3(UserLogin user) {
		if (!StringUtils.hasText(user.getUserNo())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(ResponseBody.FAILURE)//
					.setMessage("[userNo]不能为空");
		}
		if (!StringUtils.hasText(user.getAddress())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(ResponseBody.FAILURE)//
					.setMessage("[address]不能为空");
		}
		if (user.getLatitude() == null) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(ResponseBody.FAILURE)//
					.setMessage("[latitude]不能为空");
		}
		if (user.getLongitude() == null) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(ResponseBody.FAILURE)//
					.setMessage("[longitude]不能为空");
		}
		log.info("定时打卡>>>{}", user);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("appid", "oa");
		ResponseBody<Map<String, Object>> body = this.restTemplate
				.exchange(this.properties.getInitStaffClockUrl() + user.getUserNo(), HttpMethod.GET,
						new HttpEntity<>(headers), ResponseBody.class)
				.getBody();
		Map<String, Object> data = body.getData();
		if (CollectionUtils.isEmpty(data)) {
			log.error("定时打卡异常>>>{}", data);
		} else {
			boolean flag = false;
			if (user.getAmpm() == UserLogin.AM) {
				flag = data.get("clockWorkOnStatus") == null;
			} else if (user.getAmpm() == UserLogin.PM) {
				flag = data.get("clockWorkOffStatus") == null;
			}
			if (flag) {
				ThreadLocalRandom random = ThreadLocalRandom.current();
				AppStaffClockVO vo = new AppStaffClockVO()//
						.setAddress(user.getAddress())//
						.setLatitude(user.getLatitude().add(BigDecimal.valueOf(random.nextFloat())).setScale(7,
								RoundingMode.HALF_UP))//
						.setLongitude(user.getLongitude().add(BigDecimal.valueOf(random.nextFloat())).setScale(7,
								RoundingMode.HALF_UP))//
						.setStaffNo(user.getUserNo())//
						.setClockTime(Date.from(user.getClockTime().atZone(ZoneId.systemDefault()).toInstant()));
				body = this.restTemplate.postForEntity(this.properties.getStaffClockUrl(),
						new HttpEntity<>(vo, headers), ResponseBody.class).getBody();
			} else {
				log.info("跳过本次打卡");
				body.setData(null).setMessage("跳过本次打卡");
			}
		}
		return body;
	}

	@Override
	public ResponseBody<?> selectAppStaffClockLogList(UserLogin userLogin) {
		if (!StringUtils.hasText(userLogin.getUserNo())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(ResponseBody.FAILURE)//
					.setMessage("[userNo]不能为空");
		}
		log.info("{}", userLogin);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("appid", "oa");
		SqlExecQueryDTO sqlExecQuery = new SqlExecQueryDTO().setSourceId(this.properties.getBiSqlSourceId());
		if (userLogin.getDates() != null && userLogin.getDates().length == 2) {
			sqlExecQuery.setCommand(String.format(this.properties.getSelectAppStaffClockLogSql(), userLogin.getUserNo(),
					userLogin.getDates()[0], userLogin.getDates()[1]));
		} else {
			LocalDate begin = LocalDate.now().withDayOfMonth(1);
			LocalDate end = LocalDate.now();
			sqlExecQuery.setCommand(
					String.format(this.properties.getSelectAppStaffClockLogSql(), userLogin.getUserNo(), begin, end));
		}
		log.info("{}", sqlExecQuery);
		ResponseBody<?> body = this.restTemplate.postForEntity(this.properties.getBiSqlExecUrl(),
				new HttpEntity<>(sqlExecQuery, headers), ResponseBody.class).getBody();
		return body;
	}

	@Override
	public ResponseBody<?> selectDeviceList(UserLogin userLogin) {
		if (!StringUtils.hasText(userLogin.getUserNo())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(ResponseBody.FAILURE)//
					.setMessage("[userNo]不能为空");
		}
		log.info("{}", userLogin);
		HttpHeaders headers = new HttpHeaders();
		headers.set("appid", "oa");
		ResponseBody<?> body = this.restTemplate.exchange(this.properties.getDeviceListUrl() + userLogin.getUserNo(),
				HttpMethod.GET, new HttpEntity<>(headers), ResponseBody.class).getBody();
		return body;
	}

	@Override
	public ResponseBody<?> resetBindDevice(String staffNo, Integer id) {
		log.info("staffNo={}, id={}", staffNo, id);
		HttpHeaders headers = new HttpHeaders();
		headers.set("appid", "oa");
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
		params.add("staffNo", staffNo);
		params.add("id", id);
		ResponseBody<?> body = this.restTemplate.postForObject(this.properties.getResetBindDeviceIdUrl(),
				new HttpEntity<>(params, headers), ResponseBody.class);
		log.info("{}", body);
		if (body.getStatus() != ResponseBody.SUCCESS) {
			return body;
		}
		return this.selectDeviceList(new UserLogin().setUserNo(staffNo));
	}

	@Override
	public ResponseBody<?> pauseJob() throws SchedulerException {
		JobConfig config = this.properties.getJobConfig();
		TriggerKey triggerKey = TriggerKey.triggerKey(config.getTriggerKey());
		TriggerState state = this.scheduler.getTriggerState(triggerKey);
		// 正常
		if (state == TriggerState.NORMAL) {
			List<UserLogin> users = (List<UserLogin>) this.scheduler.getTrigger(triggerKey).getJobDataMap()
					.get(config.getJobDataKey());
			this.scheduler.pauseJob(JobKey.jobKey(config.getJobKey()));
			return new ResponseBody<>()//
					.setCode(HttpStatus.OK.value())//
					.setStatus(ResponseBody.SUCCESS)//
					.setMessage(String.format("暂停定时任务成功，当前任务包含的员工%s",
							users.stream().map(m -> String.join("=", m.getUserNo(), m.getUsername(), m.getAddress()))
									.collect(Collectors.toList())));
		}
		// 不在以上任何状态之内
		return new ResponseBody<>()//
				.setCode(HttpStatus.BAD_REQUEST.value())//
				.setStatus(ResponseBody.FAILURE)//
				.setMessage(String.format("当前任务状态[%s]无法暂停", state));
	}

	@Override
	public ResponseBody<?> resumeJob() throws SchedulerException {
		JobConfig config = this.properties.getJobConfig();
		TriggerKey triggerKey = TriggerKey.triggerKey(config.getTriggerKey());
		TriggerState state = this.scheduler.getTriggerState(triggerKey);
		// 暂停
		if (state == TriggerState.PAUSED) {
			List<UserLogin> users = (List<UserLogin>) this.scheduler.getTrigger(triggerKey).getJobDataMap()
					.get(config.getJobDataKey());
			this.scheduler.resumeJob(JobKey.jobKey(config.getJobKey()));
			return new ResponseBody<>()//
					.setCode(HttpStatus.OK.value())//
					.setStatus(ResponseBody.SUCCESS)//
					.setMessage(String.format("恢复定时任务成功，当前任务包含的员工%s",
							users.stream().map(m -> String.join("=", m.getUserNo(), m.getUsername(), m.getAddress()))
									.collect(Collectors.toList())));
		}
		// 不在以上任何状态之内
		return new ResponseBody<>()//
				.setCode(HttpStatus.BAD_REQUEST.value())//
				.setStatus(ResponseBody.FAILURE)//
				.setMessage(String.format("当前任务状态[%s]无法恢复", state));
	}

	@Override
	public ResponseBody<?> saveJob(JobInfo jobInfo) throws SchedulerException {
		JobConfig config = this.properties.getJobConfig();
		TriggerKey triggerKey = TriggerKey.triggerKey(config.getTriggerKey());
		TriggerState state = this.scheduler.getTriggerState(triggerKey);
		// 不存在，创建
		if (state == TriggerState.NONE) {
			if (CollectionUtils.isEmpty(jobInfo.getUsers())) {
				return new ResponseBody<>()//
						.setCode(HttpStatus.BAD_REQUEST.value())//
						.setStatus(ResponseBody.FAILURE)//
						.setMessage("[users]不能为空！");
			}
			if (!StringUtils.hasText(jobInfo.getCronExpression())) {
				return new ResponseBody<>()//
						.setCode(HttpStatus.BAD_REQUEST.value())//
						.setStatus(ResponseBody.FAILURE)//
						.setMessage("[cronExpression]不能为空！");
			}
			CronScheduleBuilder cronSchedule = null;
			try {
				cronSchedule = CronScheduleBuilder.cronSchedule(jobInfo.getCronExpression());
			} catch (Exception e) {
				return new ResponseBody<>()//
						.setCode(HttpStatus.BAD_REQUEST.value())//
						.setStatus(ResponseBody.FAILURE)//
						.setMessage(e.getLocalizedMessage());
			}
			CronTrigger trigger = TriggerBuilder.newTrigger()//
					.forJob(this.jobDetail)//
					.withIdentity(config.getTriggerKey())//
					.withSchedule(cronSchedule)//
					.build();
			trigger.getJobDataMap().put(config.getJobDataKey(), jobInfo.getUsers());
			this.scheduler.scheduleJob(trigger);
			return new ResponseBody<>()//
					.setCode(HttpStatus.OK.value())//
					.setStatus(ResponseBody.SUCCESS)//
					.setMessage("创建定时任务成功");
		}
		// 正常 或 暂停
		if (state == TriggerState.NORMAL || state == TriggerState.PAUSED) {
			if (CollectionUtils.isEmpty(jobInfo.getUsers())) {
				return new ResponseBody<>()//
						.setCode(HttpStatus.BAD_REQUEST.value())//
						.setStatus(ResponseBody.FAILURE)//
						.setMessage("[users]不能为空！");
			}
			if (!StringUtils.hasText(jobInfo.getCronExpression())) {
				return new ResponseBody<>()//
						.setCode(HttpStatus.BAD_REQUEST.value())//
						.setStatus(ResponseBody.FAILURE)//
						.setMessage("[cronExpression]不能为空！");
			}
			CronScheduleBuilder cronSchedule = null;
			try {
				cronSchedule = CronScheduleBuilder.cronSchedule(jobInfo.getCronExpression());
			} catch (Exception e) {
				return new ResponseBody<>()//
						.setCode(HttpStatus.BAD_REQUEST.value())//
						.setStatus(ResponseBody.FAILURE)//
						.setMessage(e.getLocalizedMessage());
			}
			CronTrigger trigger = (CronTrigger) this.scheduler.getTrigger(triggerKey);
			trigger = trigger.getTriggerBuilder()//
					.withIdentity(triggerKey)//
					.withSchedule(cronSchedule)//
					.build();
			trigger.getJobDataMap().put(config.getJobDataKey(), jobInfo.getUsers());
			this.scheduler.rescheduleJob(triggerKey, trigger);
			return new ResponseBody<>()//
					.setCode(HttpStatus.OK.value())//
					.setStatus(ResponseBody.SUCCESS)//
					.setMessage("更新定时任务成功");
		}
		// 完成
		if (state == TriggerState.COMPLETE) {
			return null;
		}
		// 错误
		if (state == TriggerState.ERROR) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(ResponseBody.FAILURE)//
					.setMessage(String.format("当前任务状态[%s]错误", state));
		}
		// 阻塞，处理中
		if (state == TriggerState.BLOCKED) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(ResponseBody.FAILURE)//
					.setMessage(String.format("当前任务状态[%s]处理中", state));
		}
		// 不在以上任何状态之内
		return new ResponseBody<>()//
				.setCode(HttpStatus.BAD_REQUEST.value())//
				.setStatus(ResponseBody.FAILURE)//
				.setMessage(String.format("未知的任务状态[%s]", state));
	}

}
