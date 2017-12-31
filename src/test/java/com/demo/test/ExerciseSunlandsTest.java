package com.demo.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unchecked")
public class ExerciseSunlandsTest {
	private RestTemplate restTemplate = new RestTemplate();
	private HttpHeaders headers = new HttpHeaders();
	private ObjectMapper mapper = new ObjectMapper();
	private static final String PARENT = "E:\\projects\\zww\\mybatis\\src\\test\\resources";
	private String fileName;

	@Before
	public void init() {
		headers.setContentType(MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8"));
		List<String> cookies = new ArrayList<>();
		cookies.add("stuToken=ec9f3ea001b20c80ca63e3db1a1af0f3");
		cookies.add("JSESSIONID=45FECAE8B94F3264D30B17210983C39A");
		headers.put(HttpHeaders.COOKIE, cookies);
		fileName = "retrievePaperUserRecords_1221";
	}

	@Test
	public void retrievePaperUserRecords() {
		try {
			String url = "http://exercise.sunlands.com/exercise/student/retrievePaperUserRecords";
			MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
			param.add("paperId", "7714");
			param.add("recordId", "1948412");
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(param, headers);
			Map<String, Object> map = this.restTemplate.postForObject(url, entity, Map.class);
			List<Map<String, Object>> data = (List<Map<String, Object>>) map.get("data");
			File file = new File(PARENT, fileName + ".json");
			this.mapper.writeValue(file, data);
			System.err.println("OK!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void readJsonFile() {
		try {
			List<Map<String, Object>> data = this.mapper.readValue(new File(PARENT, fileName + ".json"), List.class);
			List<String> lines = new ArrayList<>();
			data.forEach(x -> {
				Integer sequence = (Integer) x.get("sequence");// 序号
				String content = (String) x.get("content");// 题目
				List<Map<String, Object>> resPaperQuestionOptionsList = (List<Map<String, Object>>) x
						.get("resPaperQuestionOptionsList");// 选项
				lines.add(sequence + "、" + content);
				resPaperQuestionOptionsList.forEach(y -> {
					String optionTitle = (String) y.get("optionTitle");// 选项字母
					String _content = (String) y.get("content");// 选项内容
					Integer isCorrect = (Integer) y.get("isCorrect");// 是否为正确选项
					lines.add("\t" + optionTitle + " " + _content + (isCorrect == 1 ? "（√）" : ""));
				});
			});
			FileUtils.writeLines(new File(PARENT, fileName + ".txt"), lines);
			System.err.println("OK!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
