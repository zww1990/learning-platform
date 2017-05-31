package com.cfilmcloud.test;

import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unchecked")
public class McxiaoxianerTest {
	private static final String DANCE_SHOW_URL = "http://www.dj66.net/index.php/ajax/dance_show";

	@Test
	public void writeFile() {
		String cssQuery = "div.header_new > div.content_special > div.right > ul#list > li > div.song > span.cbox > input";
		try {
			for (int i = 1; i <= 7; i++) {
				String url = "http://www.dj66.net/zj----mcxiaoxianer----" + i + ".html";
				Element body = Jsoup.connect(url).get().body();
				List<String> list = body.select(cssQuery).stream().map(x -> x.val()).collect(Collectors.toList());
				String did = StringUtils.collectionToCommaDelimitedString(list);
				Document doc = Jsoup.connect(DANCE_SHOW_URL).data("did", did).post();
				String text = doc.body().text();
				File file = new File("D:\\Projects\\zww\\mybatis\\src\\test\\resources\\mcxiaoxianer" + i + ".json");
				FileUtils.writeStringToFile(file, text, "UTF-8");
			}
			System.err.println("ok!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static final String URL_PREFIX = "http://dpn.sosotxt.com";
	private static final String FILE_DIR_PREFIX = "F:\\TDDOWNLOAD\\Mc小仙儿-自慰催眠\\";
	private static final String REGEX = "[\\/:*?\"<>|]";

	@Test
	public void readFile() {
		try {
			int seq = 7;
			ObjectMapper mapper = new ObjectMapper();
			ClassPathResource resource = new ClassPathResource("mcxiaoxianer" + seq + ".json");
			Map<String, Map<String, String>> map = mapper.readValue(resource.getInputStream(), Map.class);
			Collection<Map<String, String>> values = map.values();
			values.stream().forEach(v -> {
				String dance_name = v.get("dance_name");// 文件名称
				String file_path = URL_PREFIX + v.get("file_path");// 文件路径
				String class_id = v.get("class_id");// 类别ID
				dance_name = dance_name.replaceAll(REGEX, "");
				try {
					String file_ext = StringUtils.getFilenameExtension(file_path);
					File destination = new File(FILE_DIR_PREFIX + class_id, dance_name + '.' + file_ext);
					if (destination.exists()) {// 如果该文件已存在，直接跳过
						System.err.println(dance_name + "|已存在");
						return;
					}
					System.err.println(dance_name + "|正在下载");
					URL source = new URL(file_path);
					FileUtils.copyURLToFile(source, destination);
					System.err.println(dance_name + "|下载完成");
				} catch (Exception e) {
					System.err.println(dance_name + "|下载出错|" + file_path);
				}
			});
			System.err.println("ok!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
