package com.demo.test;

import java.io.File;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

/**
 * @author Alienware
 * @date 2017年2月13日,下午7:38:37
 * @see
 * @serial
 * @since
 * @version
 */
@SuppressWarnings("unchecked")
public class CninfoTest {
	private RestTemplate restTemplate = new RestTemplate();
	private HttpHeaders headers = new HttpHeaders();

	@Before
	public void init() {
		headers.setContentType(MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8"));
		headers.add("Accept", "application/json, text/javascript, */*; q=0.01");
		headers.add("Accept-Encoding", "gzip, deflate");
		headers.add("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		headers.add("Connection", "keep-alive");
		headers.add("Host", "www.cninfo.com.cn");
		headers.add("Referer", "http://www.cninfo.com.cn/cninfo-new/announcement/show");
		headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:51.0) Gecko/20100101 Firefox/51.0");
		headers.add("X-Requested-With", "XMLHttpRequest");
	}

	@Test
	public void query() {
		try {
			String query_url = "http://www.cninfo.com.cn/cninfo-new/announcement/query";
			MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
			param.add("stock", "");
			param.add("searchkey", "2017;");
			param.add("plate", "");
			param.add("category", "");
			param.add("trade", "");
			param.add("column", "szse");
			param.add("columnTitle", "历史公告查询");
			param.add("pageNum", "1");
			param.add("pageSize", "50");
			param.add("tabName", "fulltext");
			param.add("sortName", "");
			param.add("sortType", "");
			param.add("limit", "");
			param.add("showTitle", "-1/searchkey/2017");
			param.add("seDate", "请选择日期");
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(param, headers);
			Map<String, Object> map = this.restTemplate.postForObject(query_url, entity, Map.class);
			boolean hasMore = (boolean) map.get("hasMore");
			System.out.println(hasMore);
			List<Map<String, Object>> announcements = (List<Map<String, Object>>) map.get("announcements");
			String format = "http://www.cninfo.com.cn/cninfo-new/disclosure/szse/bulletin_detail/true/%s?announceTime=%s";
			announcements.stream().forEach(a -> {
				String announcementId = (String) a.get("announcementId");
				String announcementTitle = (String) a.get("announcementTitle");
				announcementTitle = announcementTitle.trim();
				long announcementTime = (long) a.get("announcementTime");
				String adjunctType = (String) a.get("adjunctType");
				String url = String.format(format, announcementId, this.datetime(announcementTime));
				System.out.println(announcementTitle + "." + adjunctType + "=" + url);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	public String datetime(long milliseconds) {
		LocalDateTime dateTime = LocalDateTime.from(Instant.ofEpochMilli(milliseconds).atZone(ZoneId.systemDefault()));
		String string = dateTime.format(formatter);
		string = string.replace(" 00:00", "");
		return string;
	}

	@Test
	public void download() {
		try {
			String url = "http://www.cninfo.com.cn/cninfo-new/disclosure/szse/bulletin_detail/true/1203080456?announceTime=2017-02-14";
			URL source = new URL(url);
			String pathname = "D:\\docs\\pdf\\2017年第二次临时股东大会的法律意见书.pdf";
			File destination = new File(pathname);
			FileUtils.copyURLToFile(source, destination);
			System.out.println("ok!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
