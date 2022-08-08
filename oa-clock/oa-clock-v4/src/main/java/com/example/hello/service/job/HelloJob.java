package com.example.hello.service.job;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Resource;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.CollectionUtils;

import com.example.hello.model.ApplicationProperties;
import com.example.hello.model.ResponseBody;
import com.example.hello.model.UserLogin;
import com.example.hello.service.HelloService;
import com.example.hello.service.ws.WebSocketServer;

import lombok.extern.slf4j.Slf4j;

/**
 * HelloJob
 * 
 * @author zhang weiwei
 * @since 2022年8月6日,下午4:22:43
 */
@DisallowConcurrentExecution
@Slf4j
public class HelloJob extends QuartzJobBean {
	@Resource
	private HelloService helloService;
	@Resource
	private ApplicationProperties properties;

	@SuppressWarnings("unchecked")
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		log.info("执行任务");
		List<UserLogin> users = (List<UserLogin>) context.getMergedJobDataMap()
				.get(this.properties.getJobConfig().getJobDataKey());
		if (!CollectionUtils.isEmpty(users)) {
			ThreadLocalRandom random = ThreadLocalRandom.current();
			// 0=(AM)上午，1=(PM)下午
			int ampm = LocalTime.now().get(ChronoField.AMPM_OF_DAY);
			// 当前日期
			LocalDate date = LocalDate.now();
			for (UserLogin user : users) {
				if (ampm == UserLogin.AM) {
					user.setClockTime(LocalDateTime.of(date, //
							LocalTime.of(8, 30 + random.nextInt(0, 30), random.nextInt(1, 60))));
				} else if (ampm == UserLogin.PM) {
					user.setClockTime(LocalDateTime.of(date, //
							LocalTime.of(18, 30 + random.nextInt(0, 30), random.nextInt(1, 60))));
				}
				user.setAmpm(ampm);
				ResponseBody<?> body = this.helloService.userLoginAndStaffClockV3(user);
				try {
					WebSocketServer.sendInfo(body.toString(), null);
				} catch (IOException e) {
					log.error(e.getLocalizedMessage(), e);
				}
				log.info("{}", body);
			}
		}
		log.info("完成任务");
	}

}
