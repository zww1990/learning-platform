package com.demo.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@SuppressWarnings("unchecked")
public class ItellyouTest {
	RestTemplate template = new RestTemplate();
	HttpHeaders headers = new HttpHeaders();

	@Before
	public void init() {
		List<String> cookies = new ArrayList<>();
		cookies.add("UM_distinctid=15f2492f54f3ad-04b35e91bf6091-4c322f7c-100200-15f2492f5501ce");
		cookies.add("CNZZDATA1605814=cnzz_eid%3D736842039-1508145545-%26ntime%3D1508291804");
		cookies.add("Hm_lvt_8688ca4bc18cbc647c9c68fdaef6bc24=1508147132,1508206954,1508221889,1508290367");
		cookies.add("_ga=GA1.2.1762813602.1508147134");
		cookies.add("_gid=GA1.2.850299945.1508147134");
		cookies.add("Hm_lpvt_8688ca4bc18cbc647c9c68fdaef6bc24=1508294749");
		cookies.add("_gat=1");
		headers.put(HttpHeaders.COOKIE, cookies);
		headers.set("Accept", "*/*");
		headers.set("Accept-Encoding", "gzip, deflate, br");
		headers.set("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		headers.set("Cache-Control", "no-cache");
		headers.set("Connection", "keep-alive");
		headers.set("Content-Length", "39");
		headers.set("Host", "msdn.itellyou.cn");
		headers.set("Pragma", "no-cache");
		headers.set("X-Requested-With", "XMLHttpRequest");
		headers.set("Referer", "https://msdn.itellyou.cn/");
		headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0");
	}

	@Test
	public void index() {
		try {
			String url = "https://msdn.itellyou.cn/Category/Index";
			Map<String, String> param = new HashMap<>();
			param.put("id", "7ab5f0cb-7607-4bbe-9e88-50716dc43de6");
			HttpEntity<Map<String, String>> request = new HttpEntity<>(param, headers);
			List<Map<String, String>> data = template.postForObject(url, request, List.class);
			System.out.println(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getLang() {
		try {
			String url = "https://msdn.itellyou.cn/Category/GetLang";
			Map<String, String> param = new HashMap<>();
			param.put("id", "32854838-13ca-4f24-89dd-bdfeaaba14c5");
			HttpEntity<Map<String, String>> request = new HttpEntity<>(param, headers);
			Map<String, Object> data = template.postForObject(url, request, Map.class);
			System.out.println(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getList() {
		try {
			String url = "https://msdn.itellyou.cn/Category/GetList";
			Map<String, String> param = new HashMap<>();
			param.put("id", "32854838-13ca-4f24-89dd-bdfeaaba14c5");
			param.put("lang", "041dbbd2-c198-4523-b438-590128265d82");
			param.put("filter", "true");
			HttpEntity<Map<String, String>> request = new HttpEntity<>(param, headers);
			Map<String, Object> data = template.postForObject(url, request, Map.class);
			System.out.println(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getProduct() {
		try {
			String url = "https://msdn.itellyou.cn/Category/GetProduct";
			Map<String, String> param = new HashMap<>();
			param.put("id", "ac664811-c35f-470d-8a27-2ca8e122d4f4");
			HttpEntity<Map<String, String>> request = new HttpEntity<>(param, headers);
			Map<String, Object> data = template.postForObject(url, request, Map.class);
			System.out.println(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
