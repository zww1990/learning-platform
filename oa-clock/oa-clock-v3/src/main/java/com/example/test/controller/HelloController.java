package com.example.test.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/hello")
@Slf4j
@SuppressWarnings("rawtypes")
public class HelloController {
	@Resource
	private RestTemplate restTemplate;
	@Resource
	private ObjectMapper objectMapper;
	@Resource
	private ApplicationProperties properties;

	@GetMapping("/users")
	public ResponseBody<List<UserInfo>> getUsers() {
		Path path = Paths.get("users.json");
		if (Files.isReadable(path)) {
			try {
				List<UserInfo> list = this.objectMapper.readValue(Files.newInputStream(path),
						this.objectMapper.getTypeFactory().constructParametricType(List.class, UserInfo.class));
				log.info("????????????????????????????????????==>>{}", path);
				if (list.isEmpty()) {
					list = this.properties.getUsers();
				}
				return new ResponseBody<List<UserInfo>>()//
						.setCode(HttpStatus.OK.value())//
						.setStatus(1)//
						.setData(list);
			} catch (IOException e) {
				log.warn("????????????????????????????????????==>>{}", path);
				return new ResponseBody<List<UserInfo>>()//
						.setCode(HttpStatus.OK.value())//
						.setStatus(1)//
						.setData(this.properties.getUsers());
			}
		}
		log.info("????????????????????????????????????");
		return new ResponseBody<List<UserInfo>>()//
				.setCode(HttpStatus.OK.value())//
				.setStatus(1)//
				.setData(this.properties.getUsers());
	}

	@GetMapping("/addresses")
	public ResponseBody<List<Address>> getAddresses() {
		Path path = Paths.get("addresses.json");
		if (Files.isReadable(path) && Files.isWritable(path)) {
			try {
				List<Address> list = this.objectMapper.readValue(Files.newInputStream(path),
						this.objectMapper.getTypeFactory().constructParametricType(List.class, Address.class));
				log.info("????????????????????????????????????==>>{}", path);
				if (list.isEmpty()) {
					list = this.properties.getAddresses();
					Files.write(path, this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(list),
							StandardOpenOption.WRITE);
				} else {
					this.properties.setAddresses(list);
				}
				return new ResponseBody<List<Address>>()//
						.setCode(HttpStatus.OK.value())//
						.setStatus(1)//
						.setData(list);
			} catch (IOException e) {
				log.warn("????????????????????????????????????==>>{}", path);
				return new ResponseBody<List<Address>>()//
						.setCode(HttpStatus.OK.value())//
						.setStatus(1)//
						.setData(this.properties.getAddresses());
			}
		}
		log.info("????????????????????????????????????");
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
					.setMessage("[address]????????????");
		}
		if (address.getLatitude() == null) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[latitude]????????????");
		}
		if (address.getLongitude() == null) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[longitude]????????????");
		}
		if (address.getId() == null) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[id]????????????");
		}
		log.info("{}", address);
		this.properties.getAddresses().add(address);
		Path path = Paths.get("addresses.json");
		try {
			Files.write(path,
					this.objectMapper.writerWithDefaultPrettyPrinter()
							.writeValueAsBytes(this.properties.getAddresses()),
					StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
			log.info("??????????????????==>>{}", path);
		} catch (IOException e) {
			log.warn("??????????????????==>>{}", path);
		}
		return new ResponseBody<List<Address>>()//
				.setCode(HttpStatus.OK.value())//
				.setStatus(1);
	}

	@PostMapping("/initStaffClock")
	public ResponseBody<?> initStaffClock(@RequestBody UserLogin userLogin) {
		if (!StringUtils.hasText(userLogin.getUserNo())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[userNo]????????????");
		}
		if (!StringUtils.hasText(userLogin.getPassword())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[password]????????????");
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
			ResponseBody<AppStaffClockLogDTO> resp = this.objectMapper.readValue(json, this.objectMapper
					.getTypeFactory().constructParametricType(ResponseBody.class, AppStaffClockLogDTO.class));
			return resp;
		} catch (JsonProcessingException e) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value())//
					.setStatus(0)//
					.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		}
	}

	@PostMapping("/userLoginAndStaffClock")
	public ResponseBody<?> userLoginAndStaffClock(@RequestBody UserLogin userLogin) {
		if (!StringUtils.hasText(userLogin.getUserNo())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[userNo]????????????");
		}
		if (!StringUtils.hasText(userLogin.getPassword())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[password]????????????");
		}
		if (!StringUtils.hasText(userLogin.getAddress())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[address]????????????");
		}
		if (userLogin.getLatitude() == null) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[latitude]????????????");
		}
		if (userLogin.getLongitude() == null) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[longitude]????????????");
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
		LocalDateTime now = LocalDateTime.now();
		AppStaffClockVO vo = new AppStaffClockVO()//
				.setAddress(userLogin.getAddress())//
				.setLatitude(userLogin.getLatitude())//
				.setLongitude(userLogin.getLongitude())//
				.setStaffNo(AESUtil.encryptAES(String.join("&", userLogin.getUserNo(),
						now.format(DateTimeFormatter.ofPattern(AESUtil.FORMAT)))))
				.setClockTime(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()));
		try {
			String json = this.objectMapper.writeValueAsString(vo);
			log.info("{}", json);
			staffClock.put("data", AESUtil.encryptAES(json));
		} catch (JsonProcessingException e) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value())//
					.setStatus(0)//
					.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		}
		log.info("{}", staffClock);
		ResponseBody<?> body = this.restTemplate.postForEntity(this.properties.getStaffClockUrl(),
				new HttpEntity<>(staffClock, headers), ResponseBody.class).getBody();
		log.info("{}", body);
		String json = this.restTemplate.exchange(this.properties.getInitStaffClockUrl() + userLogin.getUserNo(),
				HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody();
		try {
			ResponseBody<AppStaffClockLogDTO> resp = this.objectMapper.readValue(json, this.objectMapper
					.getTypeFactory().constructParametricType(ResponseBody.class, AppStaffClockLogDTO.class));
			return resp;
		} catch (JsonProcessingException e) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value())//
					.setStatus(0)//
					.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		}
	}

	@PostMapping("/selectAppStaffClockLogList")
	public ResponseBody<?> selectAppStaffClockLogList(@RequestBody UserLogin userLogin) {
		if (!StringUtils.hasText(userLogin.getUserNo())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[userNo]????????????");
		}
		if (!StringUtils.hasText(userLogin.getPassword())) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(0)//
					.setMessage("[password]????????????");
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
		SqlExecQueryDTO sqlExecQuery = new SqlExecQueryDTO().setSourceId(this.properties.getBiSqlSourceId())
				.setCommand(String.format(this.properties.getSelectAppStaffClockLogSql(), userLogin.getUserNo()));
		log.info("{}", sqlExecQuery);
		ResponseBody<?> body = this.restTemplate.postForEntity(this.properties.getBiSqlExecUrl(),
				new HttpEntity<>(sqlExecQuery, headers), ResponseBody.class).getBody();
		return body;
	}
}
