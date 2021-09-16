package com.example.test.controller;

import java.math.BigDecimal;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.test.model.ApplicationProperties;
import com.example.test.model.ResponseBody;
import com.example.test.model.UserLogin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.net.yzl.oa.entity.vo.AppStaffClockVO;
import cn.net.yzl.oa.util.AESUtil;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HelloController {
	@Resource
	private RestTemplate restTemplate;
	@Resource
	private ObjectMapper objectMapper;
	@Resource
	private ApplicationProperties properties;

	@SuppressWarnings("rawtypes")
	@PostMapping({ "", "/" })
	public ResponseBody index(@RequestBody UserLogin userLogin) {
		log.info("{}", userLogin);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<Map> userLoginResponse = this.restTemplate.postForEntity(this.properties.getUserLoginUrl(),
				new HttpEntity<>(userLogin, headers), Map.class);
		List<String> tokens = userLoginResponse.getHeaders().get("token");
		if (CollectionUtils.isEmpty(tokens)) {
			return new ResponseBody().setCode(HttpStatus.UNAUTHORIZED.value()).setStatus(0)
					.setMessage(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		}
		String token = tokens.get(0);
		log.info("token={}", token);
		headers.set("token", token);
		Map<String, String> staffClock = new HashMap<>();
		LocalDateTime now = LocalDateTime.now().minusMinutes(1);
		AppStaffClockVO vo = new AppStaffClockVO().setAddress("北京市海淀区花园路街道泰兴大厦泰兴大厦(花园东路)")
				.setLatitude(BigDecimal.valueOf(39.9803540)).setLongitude(BigDecimal.valueOf(116.3689370))
				.setStaffNo(AESUtil.encryptAES(String.join("&", userLogin.getUserNo(),
						now.format(DateTimeFormatter.ofPattern(AESUtil.FORMAT)))))
				.setClockTime(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()));
		try {
			String json = this.objectMapper.writeValueAsString(vo);
			log.info("{}", json);
			staffClock.put("data", AESUtil.encryptAES(json));
		} catch (JsonProcessingException e) {
			return new ResponseBody().setCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).setStatus(0)
					.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		}
		log.info("{}", staffClock);
		ResponseEntity<ResponseBody> staffClockResponse = this.restTemplate.postForEntity(
				this.properties.getStaffClockUrl(), new HttpEntity<>(staffClock, headers), ResponseBody.class);
		ResponseBody body = staffClockResponse.getBody();
		log.info("{}", body);
		return body;
	}
}
