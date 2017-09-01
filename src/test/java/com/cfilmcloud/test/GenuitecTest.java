package com.cfilmcloud.test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unchecked")
public class GenuitecTest {
	@Test
	public void download() {
		String url = "http://www.genuitec.com/products/myeclipse/download/";
		try {
			Element body = Jsoup.connect(url).timeout(10000).get().body();
			String text = body.select("script:eq(2)").html();
			if (StringUtils.hasText(text)) {
				String[] texts = StringUtils.split(text, ";");
				List<String> data = Arrays.stream(texts).map(x -> x.substring(x.indexOf('{'), x.lastIndexOf('}') + 1))
						.collect(Collectors.toList());
				String parent = "D:\\Projects\\zww\\mybatis\\src\\test\\resources";
				String child = "genuitec.json";
				// File file = new File(parent, child);
				// FileUtils.writeLines(file, data);
				ObjectMapper om = new ObjectMapper();
				Map<String, Object> json = om.readValue(data.get(1), Map.class);
				Map<String, String> win = (Map<String, String>) json.get("win");
				win.entrySet().stream().forEach(x -> System.out.println(x.getKey() + "=" + x.getValue()));
				System.out.println("*******************************************************************");
				url = win.get("url");
				body = Jsoup.connect(url).timeout(10000).get().body();
				String cssQuery = "div#page-container > div#et-main-area > div#main-content > div.MyEclipse.me_download > div.thanks > section#thanks_me.thanks_head > div.content a";
				Elements elements = body.select(cssQuery);
				elements.stream().forEach(x -> System.out.println(x.attr("href")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
