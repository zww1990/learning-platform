package com.demo.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

/**
 * @author Alienware
 * @date 2017年8月4日,下午1:01:55
 * @see
 * @serial
 * @since
 * @version
 */
public class ZhongzisoTest {
	private static final String charsetName = "UTF-8";
	private static final String parent = "E:\\Projects\\zww\\mybatis\\src\\test\\resources";
	private static final Map<String, String> cookies = new HashMap<>();
	private static final Map<String, String> headers = new HashMap<>();
	static {
		cookies.put("CNZZDATA1261475054", "13144551-1501724184-|1501821439");
		cookies.put("Hm_lpvt_95c1c75e6738c7398ed35de9951d64e6", "1501823369");
		cookies.put("Hm_lvt_95c1c75e6738c7398ed35de9951d64e6", "1501727709,1501814097,1501823369");
		cookies.put("JXD733178", "1");
		cookies.put("JXM733178", "1");
		cookies.put("UM_distinctid", "15da5f255a0304-083f4c982162eb8-17397540-1fa400-15da5f255a11f2");
		cookies.put("__albb_cpv_r_35531_cpv_plan_ids", "|1828||1504|");
		cookies.put("__cfduid", "ddcc0768e36a070d1f74d45dd05d924091501727708");
		cookies.put("ggy_second", "true");
		cookies.put("hm_t_vis_118039", "0");
		headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		headers.put("Accept-Encoding", "gzip, deflate");
		headers.put("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		headers.put("Cache-Control", "max-age=0");
		headers.put("Connection", "keep-alive");
		headers.put("Host", "www.zhongziso.net");
		headers.put("Upgrade-Insecure-Requests", "1");
		headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:54.0) Gecko/20100101 Firefox/54.0");
	}

	@Test
	public void search() {
		int length = 13;
		List<String> values = new ArrayList<>();
		String search_url = null;
		Element body = null;
		String cssQuery = null;
		Elements elements = null;
		String title = null;
		String href = null;
		Elements torrents = null;
		Optional<Element> optional = null;
		String thunder = null;
		for (int i = 1; i <= length; i++) {
			try {
				search_url = "http://www.zhongziso.net/search/c%E4%BB%94/time-" + i + ".html";
				body = Jsoup.connect(search_url).headers(headers).cookies(cookies).get().body();
				cssQuery = "div#wrapper > div#content > div#wall > div.search-item > div.item-title > h3 > a";
				elements = body.select(cssQuery);
				for (Element element : elements) {
					title = element.attr("title");
					href = element.attr("href");
					try {
						body = Jsoup.connect(href).headers(headers).cookies(cookies).get().body();
						cssQuery = "div#wrapper > div#content > div#wall > div.fileDetail > div.detail-panel.detail-width > div.panel-body > a";
						torrents = body.select(cssQuery);
						optional = torrents.stream().filter(x -> x.attr("href").startsWith("thunder://")).findFirst();
						if (optional.isPresent()) {
							thunder = optional.get().attr("href");
							values.add(thunder);
						}
					} catch (Exception e) {
						System.err.println("ERROR=" + e.getLocalizedMessage());
						System.err.println("title=" + title);
						System.err.println("href=" + href);
					}
				}
			} catch (Exception e) {
				System.err.println("ERROR=" + e.getLocalizedMessage());
				System.err.println("Search URL=" + search_url);
			}
		}
		if (!values.isEmpty()) {
			try {
				File file = new File(parent, "zhongziso.txt");
				FileUtils.writeLines(file, charsetName, values);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("OK!");
	}

	@Test
	public void update() {
		String[] times = { "6", "11", "12" };
		List<String> values = new ArrayList<>();
		String search_url = null;
		Element body = null;
		String cssQuery = null;
		Elements elements = null;
		String title = null;
		String href = null;
		Elements torrents = null;
		Optional<Element> optional = null;
		String thunder = null;
		for (String time : times) {
			try {
				search_url = "http://www.zhongziso.net/search/c%E4%BB%94/time-" + time + ".html";
				body = Jsoup.connect(search_url).headers(headers).cookies(cookies).get().body();
				cssQuery = "div#wrapper > div#content > div#wall > div.search-item > div.item-title > h3 > a";
				elements = body.select(cssQuery);
				for (Element element : elements) {
					title = element.attr("title");
					href = element.attr("href");
					try {
						body = Jsoup.connect(href).headers(headers).cookies(cookies).get().body();
						cssQuery = "div#wrapper > div#content > div#wall > div.fileDetail > div.detail-panel.detail-width > div.panel-body > a";
						torrents = body.select(cssQuery);
						optional = torrents.stream().filter(x -> x.attr("href").startsWith("thunder://")).findFirst();
						if (optional.isPresent()) {
							thunder = optional.get().attr("href");
							values.add(thunder);
						}
					} catch (Exception e) {
						System.err.println("ERROR=" + e.getLocalizedMessage());
						System.err.println("title=" + title);
						System.err.println("href=" + href);
					}
				}
			} catch (Exception e) {
				System.err.println("ERROR=" + e.getLocalizedMessage());
				System.err.println("Search URL=" + search_url);
			}
		}
		if (!values.isEmpty()) {
			try {
				File file = new File(parent, "zhongziso.txt");
				FileUtils.writeLines(file, charsetName, values, true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("OK!");
	}
}
