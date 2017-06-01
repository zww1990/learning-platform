package com.cfilmcloud.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unchecked")
public class DanceShowTest {
	private static final String DANCE_SHOW_URL = "http://www.dj66.net/index.php/ajax/dance_show";
	private static final String PARENT = "D:\\Projects\\zww\\mybatis\\src\\test\\resources";
	private RestTemplate template = new RestTemplate();
	private HttpHeaders headers = new HttpHeaders();
	private ObjectMapper mapper = new ObjectMapper();

	@Before
	public void init() {
		headers.setContentType(MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8"));
	}

	@Test
	public void writeFile() {
		try {
			int length = 3397;
			List<Integer> ids = new ArrayList<Integer>();
			for (int i = 1; i <= length; i++) {
				ids.add(i);
			}
			MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
			param.add("did", StringUtils.collectionToCommaDelimitedString(ids));
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(param, headers);
			String string = this.template.postForObject(DANCE_SHOW_URL, entity, String.class);
			Map<String, Map<String, String>> map = this.mapper.readValue(string, Map.class);
			File file = new File(PARENT, "dance_show.json");
			this.mapper.writeValue(file, map);
			System.out.println("OK!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void readFile() {
		try {
			ClassPathResource resource = new ClassPathResource("dance_show.json");
			Map<String, Map<String, String>> map = this.mapper.readValue(resource.getFile(), Map.class);
			List<Map<String, String>> list = map.values().stream().filter(m -> {
				String dance_name = m.get("dance_name");
				String className = m.get("className");
				return className.contains("骚麦") || dance_name.contains("小仙儿") || dance_name.contains("小苮儿");
			}).collect(Collectors.toList());
			File file = new File(PARENT, "dance_show2.json");
			this.mapper.writeValue(file, list);
			System.out.println("OK!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
