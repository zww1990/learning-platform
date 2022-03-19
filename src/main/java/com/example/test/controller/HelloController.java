package com.example.test.controller;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.http.ResponseEntity;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.net.yzl.oa.entity.AppStaffClockLogDTO;
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
	private ObjectMapper objectMapper;
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
		if (!StringUtils.hasText(userLogin.getPassword())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[password]不能为空");
		}
		log.info("{}", userLogin);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<ResponseBody> userLoginResponse = this.restTemplate.postForEntity(
				this.properties.getUserLoginUrl(), new HttpEntity<>(userLogin, headers), ResponseBody.class);
		List<String> tokens = userLoginResponse.getHeaders().get("token");
		if (CollectionUtils.isEmpty(tokens)) {
			return userLoginResponse.getBody();
		}
		String token = tokens.get(0);
		log.info("token={}", token);
		headers.set("token", token);
		String json = this.restTemplate.exchange(this.properties.getInitStaffClockUrl() + userLogin.getUserNo(),
				HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody();
		try {
			ResponseBody<Object> body = this.objectMapper.readValue(json, ResponseBody.class);
			if (StringUtils.hasText(String.valueOf(body.getData()))) {
				AppStaffClockLogDTO resp = this.objectMapper
						.readValue(AESUtil.decryptAES(String.valueOf(body.getData())), AppStaffClockLogDTO.class);
				return body.setData(resp);
			}
			return body;
		} catch (JsonProcessingException e) {
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
		if (!StringUtils.hasText(userLogin.getPassword())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[password]不能为空");
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
		log.info("{}", userLogin);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<ResponseBody> userLoginResponse = this.restTemplate.postForEntity(
				this.properties.getUserLoginUrl(), new HttpEntity<>(userLogin, headers), ResponseBody.class);
		List<String> tokens = userLoginResponse.getHeaders().get("token");
		if (CollectionUtils.isEmpty(tokens)) {
			return userLoginResponse.getBody();
		}
		String token = tokens.get(0);
		log.info("token={}", token);
		headers.set("token", token);
		Map<String, String> staffClock = new HashMap<>();
		AppStaffClockVO vo = new AppStaffClockVO()//
				.setAddress(userLogin.getAddress())//
				.setLatitude(userLogin.getLatitude())//
				.setLongitude(userLogin.getLongitude())//
				.setStaffNo(AESUtil.encryptAES(String.join("&", userLogin.getUserNo(),
						userLogin.getClockTime().format(DateTimeFormatter.ofPattern(AESUtil.FORMAT)))))
				.setClockTime(Date.from(userLogin.getClockTime().atZone(ZoneId.systemDefault()).toInstant()));
		try {
			String json = this.objectMapper.writeValueAsString(vo);
			log.info("{}", json);
			staffClock.put("data", AESUtil.encryptAES(json));
		} catch (JsonProcessingException e) {
			log.error(e.getLocalizedMessage(), e);
			return new ResponseBody<>()//
					.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value())//
					.setStatus(0)//
					.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		}
		log.info("{}", staffClock);
		ResponseBody<Object> body = this.restTemplate.postForEntity(this.properties.getStaffClockUrl(),
				new HttpEntity<>(staffClock, headers), ResponseBody.class).getBody();
		log.info("{}", body);
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
		if (!StringUtils.hasText(userLogin.getPassword())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[password]不能为空");
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
		log.info("{}", userLogin);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<ResponseBody> userLoginResponse = this.restTemplate.postForEntity(
				this.properties.getUserLoginUrl(), new HttpEntity<>(userLogin, headers), ResponseBody.class);
		List<String> tokens = userLoginResponse.getHeaders().get("token");
		if (CollectionUtils.isEmpty(tokens)) {
			return userLoginResponse.getBody();
		}
		String token = tokens.get(0);
		log.info("token={}", token);
		headers.set("token", token);
		Map<String, String> staffClock = new HashMap<>();
		AppStaffClockVO vo = new AppStaffClockVO()//
				.setAddress(userLogin.getAddress())//
				.setLatitude(userLogin.getLatitude())//
				.setLongitude(userLogin.getLongitude())//
				.setStaffNo(AESUtil.encryptAES(String.join("&", userLogin.getUserNo(),
						userLogin.getClockTime().format(DateTimeFormatter.ofPattern(AESUtil.FORMAT)))))
				.setClockTime(Date.from(userLogin.getClockTime().atZone(ZoneId.systemDefault()).toInstant()));
		try {
			String json = this.objectMapper.writeValueAsString(vo);
			log.info("{}", json);
			staffClock.put("data", AESUtil.encryptAES(json));
		} catch (JsonProcessingException e) {
			log.error(e.getLocalizedMessage(), e);
			return new ResponseBody<>()//
					.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value())//
					.setStatus(0)//
					.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		}
		log.info("{}", staffClock);
		ResponseBody<Object> body = this.restTemplate.postForEntity(this.properties.getStaffClockUrl(),
				new HttpEntity<>(staffClock, headers), ResponseBody.class).getBody();
		log.info("{}", body);
		String json = this.restTemplate.exchange(this.properties.getInitStaffClockUrl() + userLogin.getUserNo(),
				HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody();
		try {
			body = this.objectMapper.readValue(json, ResponseBody.class);
			if (StringUtils.hasText(String.valueOf(body.getData()))) {
				AppStaffClockLogDTO resp = this.objectMapper
						.readValue(AESUtil.decryptAES(String.valueOf(body.getData())), AppStaffClockLogDTO.class);
				if (this.properties.getUsers().stream().noneMatch(p -> p.getUserNo().equals(userLogin.getUserNo()))) {
					this.properties.getUsers().add(//
							new UserInfo()//
									.setUserNo(userLogin.getUserNo())//
									.setUsername(userLogin.getUsername())//
									.setPassword(userLogin.getPassword()));
				}
				return body.setData(resp);
			}
			return body;
		} catch (JsonProcessingException e) {
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
		if (!StringUtils.hasText(userLogin.getPassword())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[password]不能为空");
		}
		log.info("{}", userLogin);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<ResponseBody> userLoginResponse = this.restTemplate.postForEntity(
				this.properties.getUserLoginUrl(), new HttpEntity<>(userLogin, headers), ResponseBody.class);
		List<String> tokens = userLoginResponse.getHeaders().get("token");
		if (CollectionUtils.isEmpty(tokens)) {
			return userLoginResponse.getBody();
		}
		String token = tokens.get(0);
		log.info("token={}", token);
		headers.set("token", token);
		SqlExecQueryDTO sqlExecQuery = new SqlExecQueryDTO().setSourceId(this.properties.getBiSqlSourceId());
		if (userLogin.getDates()==null||userLogin.getDates().length!=2) {
			
		} else {

		}
				sqlExecQuery.setCommand(String.format(this.properties.getSelectAppStaffClockLogSql(), userLogin.getUserNo()));
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
		headers.set("appid", "crm");
		ResponseBody body = this.restTemplate.exchange(this.properties.getDeviceListUrl() + userLogin.getUserNo(),
				HttpMethod.GET, new HttpEntity<>(headers), ResponseBody.class).getBody();
		return body;
	}

	@PostMapping("/resetbinddevice")
	public ResponseBody<?> resetBindDevice(@RequestParam String staffNo, @RequestParam Integer id) {
		log.info("staffNo={}, id={}", staffNo, id);
		HttpHeaders headers = new HttpHeaders();
		headers.set("appid", "crm");
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
