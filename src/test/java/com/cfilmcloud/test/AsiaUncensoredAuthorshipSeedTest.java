package com.cfilmcloud.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.util.StringUtils;

/**
 * @author Alienware
 * @date 2017年8月9日,下午8:41:59
 * @see
 * @serial
 * @since
 * @version
 */
public class AsiaUncensoredAuthorshipSeedTest {
	private static final Map<String, String> cookies = new HashMap<>();
	private static final Map<String, String> headers = new HashMap<>();
	private static final String prefix_url = "http://162.252.9.10/forum/";
	static {
		headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		headers.put("Accept-Encoding", "gzip, deflate");
		headers.put("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		headers.put("Connection", "keep-alive");
		headers.put("Host", "162.252.9.10");
		headers.put("Upgrade-Insecure-Requests", "1");
		headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:54.0) Gecko/20100101 Firefox/54.0");
		// cookies.put("_ga", "GA1.1.576789365.1502261359");
		// cookies.put("_gat", "1");
		// cookies.put("_gid", "GA1.1.786046367.1502261359");
		// cookies.put("cdb2_fid143", "1502259240");
		// cookies.put("cdb2_fid25", "1502246359");
		// cookies.put("cdb2_oldtopics", "D9990286D9964603D9989230D");
		// cookies.put("cdb2_sid", "FhzUGl");
	}

	@Test
	public void list() {
		int connectionTimeout = 10000;
		int readTimeout = 10000;
		String forumdisplay_url = prefix_url + "forumdisplay.php";
		String cssQuery = "div#wrapper > div > div.mainbox.threadlist > form > table:last-child > tbody > tr > th > span[id] > a";
		String href = null;
		String childCssQuery = "div#wrapper div form div.mainbox.viewthread table tbody tr td.postcontent div.postmessage.defaultpost div.box.postattachlist dl.t_attachlist dt a[href^=attachment]";
		Elements childElements = null;
		String childHref = null;
		for (int page = 1, length = 74; page <= length; page++) {
			try {
				Element body = Jsoup.connect(forumdisplay_url).timeout(connectionTimeout)
						.data("fid", "143", "filter", "type", "typeid", "76", "page", Integer.toString(page))
						.cookies(cookies).headers(headers).get().body();
				Elements elements = body.select(cssQuery);
				for (Element element : elements) {
					href = element.attr("href");
					System.out.println(element.text());
					try {
						body = Jsoup.connect(prefix_url + href).timeout(readTimeout).cookies(cookies).headers(headers)
								.get().body();
						childElements = body.select(childCssQuery);
						childHref = childElements.attr("href");
						if (StringUtils.hasText(childHref)) {
							System.out.println(prefix_url + childHref);
						} else {
							System.err.println(prefix_url + href);
						}
					} catch (Exception e) {
						System.err.println("Error=" + e.getLocalizedMessage() + ",Url=" + prefix_url + href);
					}
				}
			} catch (Exception e) {
				System.err.println("Error=" + e.getLocalizedMessage() + ",Url=" + forumdisplay_url + ",Page=" + page);
			}
			System.out.println("-------------------------------第" + page + "页结束---------------------------------");
		}
		System.out.println("OK!");
	}

	@Test
	public void detail() {
		List<String> urls = Arrays.asList();
		for (String url : urls) {
			try {
				Element body = Jsoup.connect(url).timeout(10000).cookies(cookies).headers(headers).get().body();
				String cssQuery = "div#wrapper div form div.mainbox.viewthread table tbody tr td.postcontent div.postmessage.defaultpost div a[href^=attachment]:last-child";
				Elements elements = body.select(cssQuery);
				String href = elements.attr("href");
				if (StringUtils.hasText(href)) {
					System.out.println("http://162.252.9.10/forum/" + href);
				} else {
					System.err.println(url);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
