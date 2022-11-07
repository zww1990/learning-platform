package com.example.hello;

import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * HelloService
 * 
 * @author weiwei
 * @version v1
 * @since 2022年11月7日,上午10:30:04
 */
@Slf4j
@Service
public class HelloService {
	/** 批量短信发送接口 */
	private static final String BATCH_SEND_URL = "http://apih.106i.cn:8086/smssend/batchSend";
	private static final String USERNAME = "8008002138";
	private static final String APIKEY = "201811022656";
	/** 编码格式 */
	private static final String ENCODING = "UTF-8";

	@Scheduled(cron = "${app.task.cron}")
	public void helloJob() {
		this.batchSend("18031360579", "请及时处理EHR系统待办，当前待办已超过99项！");
	}

	private void batchSend(String mobile, String text) {
		if (!StringUtils.hasText(mobile)) {
			log.error("mobile不能为空");
			return;
		}
		if (!StringUtils.hasText(text)) {
			log.error("text不能为空");
			return;
		}
		String getUrl = null;
		try {
			getUrl = String.format("%s?username=%s&apikey=%s&mobile=%s&encode=%s&content=%s", BATCH_SEND_URL, USERNAME,
					APIKEY, mobile, ENCODING, URLEncoder.encode(text, ENCODING));
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			return;
		}
		log.info("getUrl >> {}", getUrl);
		HttpGet method = new HttpGet(getUrl);
		log.info("method >> {}", method);
		try (CloseableHttpClient client = HttpClients.createDefault();
				CloseableHttpResponse response = client.execute(method);) {
			log.info("response >> {}", response);
			HttpEntity entity = response.getEntity();
			log.info("entity >> {}", entity);
			if (entity != null) {
				String entityText = EntityUtils.toString(entity, ENCODING);
				log.info("entityText >> {}", entityText);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
	}

}
