package com.demo.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unchecked")
public class ExerciseSunlandsTest {
	private RestTemplate restTemplate = new RestTemplate();
	private HttpHeaders headers = new HttpHeaders();
	private ObjectMapper mapper = new ObjectMapper();
	private static final String PARENT = "E:\\projects\\zww\\mybatis\\src\\test\\resources";
	private String fileName;

	@Before
	public void init() {
		headers.setContentType(MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8"));
		List<String> cookies = new ArrayList<>();
		cookies.add("stuToken=3e42f72daa1fc55fa791c7f4aa43033e");
		cookies.add("JSESSIONID=B5EE2764D74C0F1814C6AE6CD6C1A5DF");
		headers.put(HttpHeaders.COOKIE, cookies);
		fileName = "近代史精讲1强化-1222-闫怀北";
	}

	@Test
	public void retrievePaperUserRecords() {
		try {
			String url = "http://exercise.sunlands.com/exercise/student/retrievePaperUserRecords";
			MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
			param.add("paperId", "8404");
			param.add("recordId", "2157416");
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(param, headers);
			Map<String, Object> map = this.restTemplate.postForObject(url, entity, Map.class);
			List<Map<String, Object>> data = (List<Map<String, Object>>) map.get("data");
			File file = new File(PARENT, fileName + ".json");
			this.mapper.writeValue(file, data);
			System.err.println("OK!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void readJsonFile() {
		try (OutputStream stream = new FileOutputStream(new File(PARENT, fileName + ".docx"));
				XWPFDocument document = new XWPFDocument();) {
			List<Map<String, Object>> data = this.mapper.readValue(new File(PARENT, fileName + ".json"), List.class);
			data.forEach(x -> {
				Integer sequence = (Integer) x.get("sequence");// 序号
				String content = (String) x.get("content");// 题目
				List<Map<String, Object>> resPaperQuestionOptionsList = (List<Map<String, Object>>) x
						.get("resPaperQuestionOptionsList");// 选项
				document.createParagraph().createRun().setText(sequence + "、" + content);
				resPaperQuestionOptionsList.forEach(y -> {
					String optionTitle = (String) y.get("optionTitle");// 选项字母
					String _content = (String) y.get("content");// 选项内容
					_content = _content.startsWith(optionTitle) ? _content : optionTitle + " " + _content;
					Integer isCorrect = (Integer) y.get("isCorrect");// 是否为正确选项
					XWPFRun run = document.createParagraph().createRun();
					if (isCorrect == 1) {
						run.setBold(true);
						run.setColor("FF0000");
					}
					run.setText("       " + _content);
				});
			});
			document.write(stream);
			System.err.println("OK!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Deprecated
	public void writeWord() {
		File file = new File(PARENT, "test.docx");
		try (OutputStream stream = new FileOutputStream(file); XWPFDocument document = new XWPFDocument();) {
			XWPFParagraph paragraph = document.createParagraph();
			XWPFRun run = paragraph.createRun();
			run.setText("hello world");
			run.setColor("FF0000");
			run.setBold(true);
			document.write(stream);
			System.err.println("OK!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
