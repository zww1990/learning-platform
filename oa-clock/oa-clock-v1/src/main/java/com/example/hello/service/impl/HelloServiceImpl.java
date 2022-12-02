package com.example.hello.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.hello.config.ApplicationProperties;
import com.example.hello.config.ApplicationProperties.Address;
import com.example.hello.config.ApplicationProperties.Task;
import com.example.hello.config.ApplicationProperties.UserInfo;
import com.example.hello.model.ResponseBody;
import com.example.hello.model.UserLogin;
import com.example.hello.service.BisearchServer;
import com.example.hello.service.HelloService;
import com.example.hello.service.StaffdbService;

import cn.net.yzl.oa.entity.ClockWorkStatus;
import cn.net.yzl.oa.entity.SqlExecQueryDTO;
import cn.net.yzl.oa.entity.vo.AppStaffClockVO;
import cn.net.yzl.oa.util.AESUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * HelloServiceImpl
 * 
 * @author zhangweiwei
 * @date 2021年5月25日,下午4:15:16
 */
@SuppressWarnings("unchecked")
@Service
@Slf4j
public class HelloServiceImpl implements HelloService {
	@Autowired
	private ApplicationProperties properties;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private MailProperties mailProperties;
	@Autowired
	private BisearchServer bisearchServer;
	@Autowired
	private StaffdbService staffdbService;

	@Scheduled(cron = "${app.task.cron}")
	public void staffClockJob() {
		Task task = this.properties.getTask();
		if (!task.isEnabled()) {
			log.debug("未开启定时任务");
			return;
		}
		log.info("执行任务");
		ThreadLocalRandom random = ThreadLocalRandom.current();
		// 0=(AM)上午，1=(PM)下午
		int ampm = LocalTime.now().get(ChronoField.AMPM_OF_DAY);
		// 当前日期
		LocalDate date = LocalDate.now();
		Address addr = this.properties.getAddresses().get(0);
		this.properties.getUsers()//
				.stream()//
				.filter(p -> p.isEnabled())//
				.forEach(c -> {
					UserLogin user = new UserLogin()//
							.setUserNo(c.getUserNo())//
							.setUsername(c.getUsername())//
							.setAddress(addr.getAddress())//
							.setLatitude(addr.getLatitude())//
							.setLongitude(addr.getLongitude())//
							.setAmpm(ampm);
					boolean isClock = false;
					if (ampm == UserLogin.AM) {
						user.setClockTime(LocalDateTime.of(date, //
								LocalTime.of(8, 30 + random.nextInt(task.getAmMin(), task.getAmMax()),
										random.nextInt(1, 60))));
						isClock = true;
					} else if (ampm == UserLogin.PM) {
						user.setClockTime(LocalDateTime.of(date, //
								LocalTime.of(18, 30 + random.nextInt(0, task.getPmMax()), random.nextInt(1, 60))));
						isClock = c.isPmOn();
					}
					if (isClock) {
						try {
							ResponseBody<?> body = this.userLoginAndStaffClockV3(user);
							log.info("{}", body);
							if (StringUtils.hasText(c.getEmail())) {
								this.sendMail(c.getEmail(), body.getMessage());
							}
						} catch (Exception e) {
							log.error(e.getLocalizedMessage(), e);
							if (StringUtils.hasText(c.getEmail())) {
								this.sendMail(c.getEmail(), e.getLocalizedMessage());
							}
						}
					}
				});
		log.info("完成任务");
	}

	private void sendMail(String email, String content) {
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setFrom(this.mailProperties.getUsername());
		smm.setTo(email);
		smm.setSubject("打卡结果");
		smm.setText(content);
		this.mailSender.send(smm);
	}

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
		return new ResponseBody<>()//
				.setCode(HttpStatus.OK.value())//
				.setStatus(ResponseBody.SUCCESS);
	}

	@Override
	public ResponseBody<?> saveUser(UserInfo userInfo) {
		if (!StringUtils.hasText(userInfo.getUserNo())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(ResponseBody.FAILURE)//
					.setMessage("[userNo]不能为空");
		}
		if (!StringUtils.hasText(userInfo.getUsername())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(ResponseBody.FAILURE)//
					.setMessage("[username]不能为空");
		}
		log.info("{}", userInfo);
		this.properties.getUsers().add(userInfo);
		return new ResponseBody<>()//
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
		Map<String, String> headers = Map.of("appid", "oa");
		ResponseBody<Map<String, Object>> body = this.staffdbService.initStaffClock(userLogin.getUserNo(), headers);
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
		Map<String, String> headers = Map.of("appid", "oa");
		ThreadLocalRandom random = ThreadLocalRandom.current();
		AppStaffClockVO vo = new AppStaffClockVO()//
				.setAddress(userLogin.getAddress())//
				.setLatitude(userLogin.getLatitude().add(BigDecimal.valueOf(random.nextFloat())).setScale(7,
						RoundingMode.HALF_UP))//
				.setLongitude(userLogin.getLongitude().add(BigDecimal.valueOf(random.nextFloat())).setScale(7,
						RoundingMode.HALF_UP))//
				.setStaffNo(userLogin.getUserNo())//
				.setClockTime(Date.from(userLogin.getClockTime().atZone(ZoneId.systemDefault()).toInstant()));
		ResponseBody<?> body = this.staffdbService.staffClock(vo, headers);
		log.info("{}", body);
		String time = userLogin.getClockTime().format(DateTimeFormatter.ofPattern(AESUtil.DATEFORMAT));
		body = this.staffdbService.createYesterdayOaAttendTest(userLogin.getUserNo(), time, time, headers);
		log.info("{}", body);
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
		Map<String, String> headers = Map.of("appid", "oa");
		ThreadLocalRandom random = ThreadLocalRandom.current();
		AppStaffClockVO vo = new AppStaffClockVO()//
				.setAddress(userLogin.getAddress())//
				.setLatitude(userLogin.getLatitude().add(BigDecimal.valueOf(random.nextFloat())).setScale(7,
						RoundingMode.HALF_UP))//
				.setLongitude(userLogin.getLongitude().add(BigDecimal.valueOf(random.nextFloat())).setScale(7,
						RoundingMode.HALF_UP))//
				.setStaffNo(userLogin.getUserNo())//
				.setClockTime(new Date());
		ResponseBody<?> body = this.staffdbService.staffClock(vo, headers);
		log.info("{}", body);
		if (this.properties.getUsers().stream().noneMatch(p -> p.getUserNo().equals(userLogin.getUserNo()))) {
			this.properties.getUsers().add(//
					new UserInfo()//
							.setUserNo(userLogin.getUserNo())//
							.setUsername(userLogin.getUsername()));
		}
		return this.initStaffClock(userLogin);
	}

	private ResponseBody<?> userLoginAndStaffClockV3(UserLogin user) {
		log.info("定时打卡>>>{}", user);
		Map<String, String> headers = Map.of("appid", "oa");
		ResponseBody<?> body = this.staffdbService.initStaffClock(user.getUserNo(), headers);
		Map<String, Object> data = (Map<String, Object>) body.getData();
		if (CollectionUtils.isEmpty(data)) {
			log.error("定时打卡异常>>>{}", data);
			body.setMessage("打卡异常");
		} else {
			boolean flag = false;
			String flagName = "";
			if (user.getAmpm() == UserLogin.AM) {
				flag = data.get("clockWorkOnStatus") == null;
				flagName = "上班";
			} else if (user.getAmpm() == UserLogin.PM) {
				flag = data.get("clockWorkOffStatus") == null;
				flagName = "下班";
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
				body = this.staffdbService.staffClock(vo, headers);
				body.setMessage(String.format("%s打卡成功: [%s]", flagName, body.getData()));
			} else {
				log.info("今日已打卡，跳过本次打卡");
				body.setData(null).setMessage("今日已打卡，跳过本次打卡");
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
		Map<String, String> headers = Map.of("appid", "oa");
		ResponseBody<?> body = this.bisearchServer.bisqlExec(sqlExecQuery, headers);
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
		Map<String, String> headers = Map.of("appid", "oa");
		ResponseBody<?> body = this.staffdbService.getDeviceList(userLogin.getUserNo(), 1, 100, headers);
		return body;
	}

	@Override
	public ResponseBody<?> resetBindDevice(String staffNo, Integer id) {
		log.info("staffNo={}, id={}", staffNo, id);
		Map<String, String> headers = Map.of("appid", "oa");
		Map<String, Object> params = new HashMap<>();
		params.put("staffNo", staffNo);
		params.put("id", id);
		ResponseBody<?> body = this.staffdbService.resetBindDeviceId(params, headers);
		log.info("{}", body);
		if (body.getStatus() != ResponseBody.SUCCESS) {
			return body;
		}
		return this.selectDeviceList(new UserLogin().setUserNo(staffNo));
	}

}
