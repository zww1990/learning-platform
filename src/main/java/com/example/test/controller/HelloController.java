package com.example.test.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.test.model.ApplicationProperties;
import com.example.test.model.ApplicationProperties.Address;
import com.example.test.model.ApplicationProperties.UserInfo;
import com.example.test.model.ResponseBody;
import com.example.test.model.UserLogin;

import cn.net.yzl.oa.entity.ClockWorkStatus;
import cn.net.yzl.oa.entity.SqlExecQueryDTO;
import cn.net.yzl.oa.entity.vo.AppStaffClockVO;
import cn.net.yzl.oa.util.AESUtil;

@RestController
@RequestMapping("/hello")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class HelloController {

	private static final Logger log = LoggerFactory.getLogger(HelloController.class);

	@Resource
	private RestTemplate restTemplate;
	@Resource
	private ApplicationProperties properties;

	@GetMapping("/users")
	public ResponseBody<List<UserInfo>> getUsers() {
		log.info("从当前应用中加载配置成功");
		return new ResponseBody<List<UserInfo>>()//
				.setCode(HttpStatus.OK.value())//
				.setStatus(1)//
				.setData(this.properties.getUsers());
	}

	@GetMapping("/addresses")
	public ResponseBody<List<Address>> getAddresses() {
		log.info("从当前应用中加载配置成功");
		return new ResponseBody<List<Address>>()//
				.setCode(HttpStatus.OK.value())//
				.setStatus(1)//
				.setData(this.properties.getAddresses());
	}

	@PostMapping("/saveaddress")
	public ResponseBody<?> saveAddress(@RequestBody Address address) {
		if (!StringUtils.hasText(address.getAddress())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[address]不能为空");
		}
		if (address.getLatitude() == null) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[latitude]不能为空");
		}
		if (address.getLongitude() == null) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[longitude]不能为空");
		}
		if (address.getId() == null) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[id]不能为空");
		}
		log.info("{}", address);
		this.properties.getAddresses().add(address);
		return new ResponseBody<List<Address>>()//
				.setCode(HttpStatus.OK.value())//
				.setStatus(1);
	}

	@PostMapping("/initstaffclock")
	public ResponseBody<?> initStaffClock(@RequestBody UserLogin userLogin) {
		if (!StringUtils.hasText(userLogin.getUserNo())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[userNo]不能为空");
		}
		log.info("{}", userLogin);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("appid", "oa");
		try {
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
			log.info("{}", body.getData());
			return body;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			return new ResponseBody<>()//
					.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value())//
					.setStatus(0)//
					.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		}
	}

	@PostMapping("/v2/userloginandstaffclock")
	public ResponseBody<?> userLoginAndStaffClockV2(@RequestBody UserLogin userLogin) {
		if (!StringUtils.hasText(userLogin.getUserNo())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[userNo]不能为空");
		}
		if (!StringUtils.hasText(userLogin.getAddress())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[address]不能为空");
		}
		if (userLogin.getLatitude() == null) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[latitude]不能为空");
		}
		if (userLogin.getLongitude() == null) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[longitude]不能为空");
		}
		log.info("补卡>>>{}", userLogin);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("appid", "oa");
		AppStaffClockVO vo = new AppStaffClockVO()//
				.setAddress(userLogin.getAddress())//
				.setLatitude(userLogin.getLatitude())//
				.setLongitude(userLogin.getLongitude())//
				.setStaffNo(userLogin.getUserNo())//
				.setClockTime(Date.from(userLogin.getClockTime().atZone(ZoneId.systemDefault()).toInstant()));
		ResponseBody<Object> body = this.restTemplate
				.postForEntity(this.properties.getStaffClockUrl(), new HttpEntity<>(vo, headers), ResponseBody.class)
				.getBody();
		log.info("{}", body);
		String time = userLogin.getClockTime().format(DateTimeFormatter.ofPattern(AESUtil.DATEFORMAT));
		body = this.restTemplate
				.exchange(String.format(this.properties.getCreateOaAttendUrl(), userLogin.getUserNo(), time, time),
						HttpMethod.GET, new HttpEntity<>(headers), ResponseBody.class)
				.getBody();
		return body;
	}

	@PostMapping("/userloginandstaffclock")
	public ResponseBody<?> userLoginAndStaffClock(@RequestBody UserLogin userLogin) {
		if (!StringUtils.hasText(userLogin.getUserNo())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[userNo]不能为空");
		}
		if (!StringUtils.hasText(userLogin.getAddress())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[address]不能为空");
		}
		if (userLogin.getLatitude() == null) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[latitude]不能为空");
		}
		if (userLogin.getLongitude() == null) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[longitude]不能为空");
		}
		log.info("打卡>>>{}", userLogin);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("appid", "oa");
		AppStaffClockVO vo = new AppStaffClockVO()//
				.setAddress(userLogin.getAddress())//
				.setLatitude(userLogin.getLatitude())//
				.setLongitude(userLogin.getLongitude())//
				.setStaffNo(userLogin.getUserNo())//
				.setClockTime(Date.from(userLogin.getClockTime().atZone(ZoneId.systemDefault()).toInstant()));
		try {
			ResponseBody<Object> body = this.restTemplate.postForEntity(this.properties.getStaffClockUrl(),
					new HttpEntity<>(vo, headers), ResponseBody.class).getBody();
			log.info("{}", body);
			return this.initStaffClock(userLogin);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			return new ResponseBody<>()//
					.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value())//
					.setStatus(0)//
					.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		}
	}

	@PostMapping("/selectappstaffclockloglist")
	public ResponseBody<?> selectAppStaffClockLogList(@RequestBody UserLogin userLogin) {
		if (!StringUtils.hasText(userLogin.getUserNo())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
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
			LocalDate begin = LocalDate.now().minusMonths(1);
			LocalDate end = LocalDate.now();
			sqlExecQuery.setCommand(
					String.format(this.properties.getSelectAppStaffClockLogSql(), userLogin.getUserNo(), begin, end));
		}
		log.info("{}", sqlExecQuery);
		ResponseBody<?> body = this.restTemplate.postForEntity(this.properties.getBiSqlExecUrl(),
				new HttpEntity<>(sqlExecQuery, headers), ResponseBody.class).getBody();
		return body;
	}

	@PostMapping("/selectdevicelist")
	public ResponseBody<?> selectDeviceList(@RequestBody UserLogin userLogin) {
		if (!StringUtils.hasText(userLogin.getUserNo())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[userNo]不能为空");
		}
		log.info("{}", userLogin);
		HttpHeaders headers = new HttpHeaders();
		headers.set("appid", "oa");
		ResponseBody body = this.restTemplate.exchange(this.properties.getDeviceListUrl() + userLogin.getUserNo(),
				HttpMethod.GET, new HttpEntity<>(headers), ResponseBody.class).getBody();
		return body;
	}

	@PostMapping("/resetbinddevice")
	public ResponseBody<?> resetBindDevice(@RequestParam String staffNo, @RequestParam Integer id) {
		log.info("staffNo={}, id={}", staffNo, id);
		HttpHeaders headers = new HttpHeaders();
		headers.set("appid", "oa");
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
		params.add("staffNo", staffNo);
		params.add("id", id);
		ResponseBody body = this.restTemplate.postForObject(this.properties.getResetBindDeviceIdUrl(),
				new HttpEntity<>(params, headers), ResponseBody.class);
		if (body.getStatus() != 1) {
			return body;
		}
		return this.selectDeviceList(new UserLogin().setUserNo(staffNo));
	}
}
