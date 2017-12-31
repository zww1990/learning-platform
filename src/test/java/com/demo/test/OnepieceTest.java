package com.demo.test;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class OnepieceTest {
	private static final String charsetName = "UTF-8";
	private static final String PARENT = "e:\\Projects\\zww\\mybatis\\src\\test\\resources";

	@Test
	public void download() {
		try {
			URL source = new URL("https://one-piece.cn/anime/");
			File destination = new File(PARENT, "index.html");
			FileUtils.copyURLToFile(source, destination);
			System.out.println("OK!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void writeFile() {
		try {
			File destination = new File(PARENT, "index.html");
			Element body = Jsoup.parse(destination, charsetName).body();
			String cssQuery = "body#anime > div#page > div#contents > div#main-area > article > div.contents-section-wrap > div.contents-section-inner > div#tab-main";
			String html = body.select(cssQuery).html();
			File file = new File(PARENT, "index2.html");
			FileUtils.writeStringToFile(file, html, charsetName);
			System.out.println("OK!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void readFile() {
		try {
			File destination = new File(PARENT, "index2.html");
			Element body = Jsoup.parse(destination, charsetName).body();
			Elements tables = body.select("div.chapter > table > tbody > tr");
			List<String> titles = new ArrayList<String>();
			for (Element tab : tables) {
				String title = tab.child(0).text() + " " + tab.child(1).text();
				title = title.replace('　', ' ');
				title = title.trim();
				titles.add(title);
			}
			File file = new File(PARENT, "title2.txt");
			FileUtils.writeLines(file, charsetName, titles);
			System.out.println("OK!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
