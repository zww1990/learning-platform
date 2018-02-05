package com.demo.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unchecked")
public class XiyitikuTest {
	private ObjectMapper mapper = new ObjectMapper();
	private static final String[] options = { "A", "B", "C", "D" };
	private static final String PARENT = "E:\\projects\\zww\\mybatis\\src\\test\\resources";

	/**
	 * 课后作业
	 */
	@Test
	public void selectClassWorkInfo() {
		try (XWPFDocument document = new XWPFDocument()) {
			String paperName = null;
			String selectClassWorkInfoUrl = "http://t.xiyitiku.com/tiku-war/classwork/selectClassWorkInfo.action";
			String ajaxAnalyseClassWorkUrl = "http://t.xiyitiku.com/tiku-war/classwork/ajaxAnalyseClassWork.action";
			Map<String, String> param = new HashMap<>();
			param.put("systemNumber", "PORTAL");
			param.put("paperTypeCode", "WORK");
			param.put("userNumber", "2713997");
			param.put("paperId", "2ba9-d9db-4419-8fb5");
			param.put("field1", "257036");
			param.put("md5CallbackSignData", "c626e4028d40f18782bce7b12155a15c");
			Document doc = Jsoup.connect(selectClassWorkInfoUrl).data(param).get();
			String cssQuery = "div.container.rank-table > table.table.table-border > tbody > tr > td.curs > em";
			Elements elements = doc.body().select(cssQuery);
			for (Element ele : elements) {
				String nextworkquestionid = ele.attr("data-nextworkquestionid");// 选择题编号
				String questionnum = ele.attr("data-questionnum");// 选择题序号
				param.put("nextWorkQuestionId", nextworkquestionid);
				param.put("questionNum", questionnum);
				try {
					String body = Jsoup.connect(ajaxAnalyseClassWorkUrl).data(param).ignoreContentType(true).get()
							.text();
					Map<String, Map<String, Map<String, Object>>> data = this.mapper.readValue(body, Map.class);
					Map<String, Map<String, Object>> workPaperDTO = data.get("workPaperDTO");
					Map<String, Object> workPaper = workPaperDTO.get("workPaper");
					paperName = (String) workPaper.get("paperName");// 试卷题目
					Map<String, Object> workQuestionDTO = workPaperDTO.get("workQuestionDTO");
					Map<String, Map<String, Object>> questionResultDTO = (Map<String, Map<String, Object>>) workQuestionDTO
							.get("questionResultDTO");
					Map<String, Object> question = questionResultDTO.get("question");
					String questionContent = (String) question.get("questionContent");// 选择题题目
					document.createParagraph().createRun().setText(questionnum + "、" + questionContent);
					String resultContent = (String) question.get("resultContent");// 选择题答案
					List<Map<String, Object>> questionOptionDTO = (List<Map<String, Object>>) workQuestionDTO
							.get("questionOptionDTO");
					questionOptionDTO.stream().forEach(option -> {
						String optionId = option.get("id").toString() + ",";// 选项编号
						String optioncolContent = (String) option.get("optioncolContent");// 选项内容
						int sortOrder = (int) option.get("sortOrder");// 选项序号
						XWPFRun run = document.createParagraph().createRun();
						if (optionId.equals(resultContent)) {
							run.setBold(true);
							run.setColor("FF0000");
						}
						run.setText("       " + options[sortOrder - 1] + "、" + optioncolContent);
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try (OutputStream stream = new FileOutputStream(new File(PARENT, paperName + ".docx"))) {
				document.write(stream);
			}
			System.err.println("OK!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Deprecated
	public void ajaxAnalyseClassWork() {
		try {
			String ajaxAnalyseClassWorkUrl = "http://t.xiyitiku.com/tiku-war/classwork/ajaxAnalyseClassWork.action";
			Map<String, String> param = new HashMap<>();
			param.put("systemNumber", "PORTAL");
			param.put("paperTypeCode", "WORK");
			param.put("userNumber", "2713997");
			param.put("paperId", "ad98-8e24-4c32-8efb");
			param.put("field1", "257613");
			param.put("nextWorkQuestionId", "248496");
			param.put("questionNum", "7");
			String body = Jsoup.connect(ajaxAnalyseClassWorkUrl).data(param).ignoreContentType(true).get().text();
			Map<String, Map<String, Map<String, Object>>> data = this.mapper.readValue(body, Map.class);
			Map<String, Map<String, Object>> workPaperDTO = data.get("workPaperDTO");
			Map<String, Object> workPaper = workPaperDTO.get("workPaper");
			String paperName = (String) workPaper.get("paperName");// 试卷题目
			System.err.println(paperName);
			Map<String, Object> workQuestionDTO = workPaperDTO.get("workQuestionDTO");
			Map<String, Map<String, Object>> questionResultDTO = (Map<String, Map<String, Object>>) workQuestionDTO
					.get("questionResultDTO");
			Map<String, Object> question = questionResultDTO.get("question");
			String questionContent = (String) question.get("questionContent");// 选择题题目
			System.err.println(questionContent);
			String resultContent = (String) question.get("resultContent");// 选择题答案
			System.err.println(resultContent);
			List<Map<String, Object>> questionOptionDTO = (List<Map<String, Object>>) workQuestionDTO
					.get("questionOptionDTO");
			questionOptionDTO.stream().forEach(option -> {
				String optionId = option.get("id").toString() + ",";// 选项编号
				String optioncolContent = (String) option.get("optioncolContent");// 选项内容
				int sortOrder = (int) option.get("sortOrder");// 选项序号
				System.err.println(options[sortOrder - 1] + ":" + optionId + optioncolContent);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 随堂考
	 */
	@Test
	public void goBackAnswerCard() {
		try (XWPFDocument document = new XWPFDocument()) {
			String paperName = null;
			String goBackAnswerCardUrl = "http://t.xiyitiku.com/tiku-war/quizzes/goBackAnswerCard.action";
			String ajaxQueryResolveQuestionUrl = "http://t.xiyitiku.com/tiku-war/quizzes/ajaxQueryResolveQuestion.action";
			Map<String, String> param = new HashMap<>();
			param.put("userPaperId", "13442325");
			param.put("quizzesPaperId", "a553-f393-4a4c-bc24");
			Document doc = Jsoup.connect(goBackAnswerCardUrl).data(param).get();
			String cssQuery = "div.ri-wrap > div#hand-wr > div.wr-botm > div.div_center > span.gre";
			Elements elements = doc.body().select(cssQuery);
			for (Element ele : elements) {
				String nextquizzesquestionid = ele.attr("data-nextquizzesquestionid");
				String userquestionid = ele.attr("data-userquestionid");
				String index = ele.attr("data-index");// 选择题序号
				param.put("nextQuizzesQuestionId", nextquizzesquestionid);
				param.put("userQuestionId", userquestionid);
				try {
					String body = Jsoup.connect(ajaxQueryResolveQuestionUrl).data(param).ignoreContentType(true).get()
							.text();
					Map<String, Map<String, Map<String, Object>>> data = this.mapper.readValue(body, Map.class);
					Map<String, Map<String, Object>> quizzesPaperDTO = data.get("quizzesPaperDTO");
					Map<String, Object> quizzesPaper = quizzesPaperDTO.get("quizzesPaper");
					paperName = (String) quizzesPaper.get("paperName");// 试卷题目
					Map<String, Object> quizzesQuestionDTO = quizzesPaperDTO.get("quizzesQuestionDTO");
					Map<String, Map<String, Object>> questionResultDTO = (Map<String, Map<String, Object>>) quizzesQuestionDTO
							.get("questionResultDTO");
					Map<String, Object> question = questionResultDTO.get("question");
					String questionContent = (String) question.get("questionContent");// 选择题题目
					document.createParagraph().createRun().setText(index + "、" + questionContent);
					String resultContent = (String) question.get("resultContent");// 选择题答案
					List<Map<String, Object>> questionOptionList = (List<Map<String, Object>>) questionResultDTO
							.get("questionOptionList");
					questionOptionList.stream().forEach(option -> {
						String optionId = option.get("id").toString() + ",";// 选项编号
						String optioncolContent = (String) option.get("optioncolContent");// 选项内容
						int sortOrder = (int) option.get("sortOrder");// 选项序号
						XWPFRun run = document.createParagraph().createRun();
						if (optionId.equals(resultContent)) {
							run.setBold(true);
							run.setColor("FF0000");
						}
						run.setText("       " + options[sortOrder - 1] + "、" + optioncolContent);
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try (OutputStream stream = new FileOutputStream(new File(PARENT, paperName + ".docx"))) {
				document.write(stream);
			}
			System.err.println("OK!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Deprecated
	public void ajaxQueryResolveQuestion() {
		try {
			String ajaxQueryResolveQuestionUrl = "http://t.xiyitiku.com/tiku-war/quizzes/ajaxQueryResolveQuestion.action";
			Map<String, String> param = new HashMap<>();
			param.put("userPaperId", "11812525");
			param.put("quizzesPaperId", "ab71-de8b-4230-8929");
			param.put("nextQuizzesQuestionId", "225152");
			param.put("userQuestionId", "131696417");
			String body = Jsoup.connect(ajaxQueryResolveQuestionUrl).data(param).ignoreContentType(true).get().text();
			Map<String, Map<String, Map<String, Object>>> data = this.mapper.readValue(body, Map.class);
			Map<String, Map<String, Object>> quizzesPaperDTO = data.get("quizzesPaperDTO");
			Map<String, Object> quizzesPaper = quizzesPaperDTO.get("quizzesPaper");
			String paperName = (String) quizzesPaper.get("paperName");// 试卷题目
			System.err.println(paperName);
			Map<String, Object> quizzesQuestionDTO = quizzesPaperDTO.get("quizzesQuestionDTO");
			Map<String, Map<String, Object>> questionResultDTO = (Map<String, Map<String, Object>>) quizzesQuestionDTO
					.get("questionResultDTO");
			Map<String, Object> question = questionResultDTO.get("question");
			String questionContent = (String) question.get("questionContent");// 选择题题目
			System.err.println(questionContent);
			String resultContent = (String) question.get("resultContent");// 选择题答案
			System.err.println(resultContent);
			List<Map<String, Object>> questionOptionList = (List<Map<String, Object>>) questionResultDTO
					.get("questionOptionList");
			questionOptionList.stream().forEach(option -> {
				String optionId = option.get("id").toString() + ",";// 选项编号
				String optioncolContent = (String) option.get("optioncolContent");// 选项内容
				int sortOrder = (int) option.get("sortOrder");// 选项序号
				System.err.println(options[sortOrder - 1] + ":" + optionId + optioncolContent);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
