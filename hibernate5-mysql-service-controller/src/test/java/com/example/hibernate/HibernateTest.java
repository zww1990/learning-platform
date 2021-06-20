package com.example.hibernate;

import java.io.File;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class HibernateTest {

	private static final String local = "E:/TDDOWNLOAD/hibernate/userguide/";
	private static final String remote = "http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/";

	@Test
	public void downDocs() {
		search(remote);
		System.out.println("下载完成！");
	}

	private void search(String url) {
		Document doc = null;
		try {
			System.out.println(url);
			doc = Jsoup.connect(url).get();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return;
		}
		Elements body = doc.body().select("table > tbody > tr > td > a");
		if (body.isEmpty()) {
			return;
		}
		body.stream().filter(a -> !a.text().contentEquals("Parent Directory")).forEach(b -> {
			String path = b.attr("href");
			String fullPath = url + path;
			if (FilenameUtils.getExtension(path).isEmpty()) {
				search(fullPath);
			} else {
				try {
					URL source = new URL(fullPath);
					File destination = new File(fullPath.replace(remote, local));
					FileUtils.copyURLToFile(source, destination);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
