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

import javax.annotation.Resource;

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

import com.example.hello.config.ApplicationProperties;
import com.example.hello.config.ApplicationProperties.Address;
import com.example.hello.config.ApplicationProperties.UserInfo;
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
 * @author zhangweiwei
 * @date 2021年5月25日,下午4:15:16
 */
@SuppressWarnings("unchecked")
@Service
@Slf4j
public class HelloServiceImpl implements HelloService {
	@Resource
	private RestTemplate restTemplate;
	@Resource
	private ApplicationProperties properties;

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

}
