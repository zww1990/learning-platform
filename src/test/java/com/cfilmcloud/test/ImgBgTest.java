package com.cfilmcloud.test;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

public class ImgBgTest {
	@Test
	public void download() {
		try {
			int length = 30;
			int index = 1;
			File parent = new File("F:\\TDDOWNLOAD\\img_bg");
			parent.mkdirs();
			for (int i = index; i <= length; i++) {
				Resource resource = new UrlResource("http://w.qq.com/img/bg/" + i + ".jpg");
				if (resource.exists()) {
					String child = resource.getFilename();
					File destination = new File(parent, child);
					InputStream source = resource.getInputStream();
					FileUtils.copyInputStreamToFile(source, destination);
					System.err.println(child + "|下载完成");
				}
			}
			System.out.println("OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
