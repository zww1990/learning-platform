package com.example.eurekaconsumer;

import java.io.InputStream;

import javax.net.ssl.HttpsURLConnection;

import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.TrustAllStrategy;
import org.apache.hc.core5.ssl.SSLContexts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import com.example.provider.api.service.HelloService;

@SpringBootTest
public class HelloServiceTests {
	@Autowired
	private HelloService helloService;

	/**
	 * 添加jvm参数：<br>
	 * --add-opens java.base/sun.net.www.protocol.http=ALL-UNNAMED
	 */
	@Test
	public void testUpload() {
		try {
			HttpsURLConnection.setDefaultHostnameVerifier(NoopHostnameVerifier.INSTANCE);
			HttpsURLConnection.setDefaultSSLSocketFactory(//
					SSLContexts.custom()//
							.loadTrustMaterial(null, TrustAllStrategy.INSTANCE)//
							.build()//
							.getSocketFactory());
			String url = "https://pics0.baidu.com/feed/b64543a98226cffcc2fdd49639b80c9cf703ea01.jpeg";
			UrlResource resource = new UrlResource(url);
			System.err.println(resource);
			InputStream input = resource.getInputStream();
			System.err.println(input);
			String name = url.substring(url.lastIndexOf('/') + 1);
			MockMultipartFile file = new MockMultipartFile(name, name, MediaType.APPLICATION_OCTET_STREAM_VALUE, input);
			System.err.println(this.helloService.upload(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
