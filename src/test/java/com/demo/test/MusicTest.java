package com.demo.test;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
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
public class MusicTest {
	private static final String MUSIC_URL = "http://mcxiaoxianer.i.dj66.net/music/";
	private static final String DANCE_SHOW_URL = "http://www.dj66.net/index.php/ajax/dance_show";
	private static final String PARENT = "D:\\Projects\\zww\\mybatis\\src\\test\\resources";

	@Test
	public void writeFile() {
		int length = 10;
		String cssQuery = "div.spaceMain > div.mainCenter > div.publicLeft > div.stageBox > div.stageBoxCenter > div.danceFavoritesList > form#list > ul.share_list > li > div.song > span.cbox > input";
		try {
			List<String> valueList = new ArrayList<String>();
			for (int i = 1; i <= length; i++) {
				Element body = Jsoup.connect(MUSIC_URL + i).get().body();
				List<String> list = body.select(cssQuery).stream().map(x -> x.val()).collect(Collectors.toList());
				valueList.addAll(list);
			}
			String did = StringUtils.collectionToCommaDelimitedString(valueList);
			Document doc = Jsoup.connect(DANCE_SHOW_URL).data("did", did).post();
			String text = doc.body().text();
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Map<String, String>> valueMap = mapper.readValue(text, Map.class);
			File file = new File(PARENT, "music.json");
			mapper.writeValue(file, valueMap);
			System.err.println("ok!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static final String URL_PREFIX = "http://dpn.sosotxt.com";
	private static final String FILE_DIR_PREFIX = "F:\\TDDOWNLOAD\\Mc小仙儿-music\\";
	private static final String REGEX = "[\\/:*?\"<>|]";

	@Test
	public void readFile() {
		try {
			int seq = 10;
			ObjectMapper mapper = new ObjectMapper();
			ClassPathResource resource = new ClassPathResource("music" + seq + ".json");
			Map<String, Map<String, String>> map = mapper.readValue(resource.getInputStream(), Map.class);
			Collection<Map<String, String>> values = map.values();
			System.err.println("开始下载。。。");
			values.stream().forEach(v -> {
				String dance_name = v.get("dance_name");// 文件名称
				String file_path = URL_PREFIX + v.get("file_path");// 文件路径
				String class_id = v.get("class_id");// 类别ID
				dance_name = dance_name.replaceAll(REGEX, "");
				try {
					String file_ext = StringUtils.getFilenameExtension(file_path);
					File destination = new File(FILE_DIR_PREFIX + class_id, dance_name + '.' + file_ext);
					if (destination.exists()) {// 如果该文件已存在，直接跳过
						// System.err.println(dance_name + "|已存在");
						return;
					}
					// System.err.println(dance_name + "|正在下载");
					URL source = new URL(file_path);
					FileUtils.copyURLToFile(source, destination);
					// System.err.println(dance_name + "|下载完成");
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
