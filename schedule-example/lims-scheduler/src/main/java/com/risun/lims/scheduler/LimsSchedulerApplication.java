package com.risun.lims.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 * @author 张维维
 * @since 2024-11-26 15:16:00
 */
@SpringBootApplication
@Slf4j
@MapperScan(annotationClass = Mapper.class)
@EnableScheduling
public class LimsSchedulerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LimsSchedulerApplication.class, args);
		log.info("Spring Bean Definition Count = {}", context.getBeanDefinitionCount());
	}

}
