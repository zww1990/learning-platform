package com.cfilmcloud.test;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.springframework.util.StringUtils;

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
				String child = "genuitec%d.json";
				for (int i = 0; i < data.size(); i++) {
					File file = new File(parent, String.format(child, i + 1));
					String value = data.get(i);
					FileUtils.writeStringToFile(file, value, Charset.defaultCharset());
				}
			}
			System.out.println("OK!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
