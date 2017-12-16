package com.demo.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

/**
 * @author Alienware
 * @date 2017年8月6日,上午1:27:46
 * @see
 * @serial
 * @since
 * @version
 */
public class Zhongziso2Test {
	private static final String charsetName = "UTF-8";
	private static final String parent = "E:\\Projects\\zww\\mybatis\\src\\test\\resources";
	private static final Map<String, String> cookies = new HashMap<>();
	private static final Map<String, String> headers = new HashMap<>();
	static {
		cookies.put("Hm_lpvt_bf527c8e99a212fc0d7f77228e7bee30", "1501953432");
		cookies.put("Hm_lvt_bf527c8e99a212fc0d7f77228e7bee30", "1501475479,1501479692,1501503096,1501953042");
		cookies.put("PHPSESSID", "c0p9vd5qce2bkb56fvdkq8hia0");
		cookies.put("__cfduid", "db206c3c08c2208b553699f5412635a4d1501249623");
		headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		headers.put("Accept-Encoding", "gzip, deflate, br");
		headers.put("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		headers.put("Cache-Control", "max-age=0");
		headers.put("Connection", "keep-alive");
		headers.put("Host", "www.zhongziso.com");
		headers.put("Referer", "https://www.zhongziso.com/");
		headers.put("Upgrade-Insecure-Requests", "1");
		headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:54.0) Gecko/20100101 Firefox/54.0");
	}

	@Test
	public void list() {
		int length = 9;
		String cssQuery = "div#wrapp > div.jumbotron > div.container > div.inerTop > div.row > div.panel > div.panel-body > table.table > tbody > tr > td.ls-magnet > a";
		List<String> values = new ArrayList<>();
		String list_url = "https://www.zhongziso.com/list/c%E4%BB%94/";
		for (int i = 1; i <= length; i++) {
			try {
				Element body = Jsoup.connect(list_url + i).cookies(cookies).headers(headers).get().body();
				Elements elements = body.select(cssQuery);
				elements.stream().forEach(x -> values.add(x.attr("href")));
			} catch (Exception e) {
				System.err.println("ERROR=" + e.getLocalizedMessage());
				System.err.println("List URL=" + list_url + i);
			}
		}
		if (!values.isEmpty()) {
			try {
				File file = new File(parent, "zhongziso2.txt");
				FileUtils.writeLines(file, charsetName, values);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("OK!");
	}
}
