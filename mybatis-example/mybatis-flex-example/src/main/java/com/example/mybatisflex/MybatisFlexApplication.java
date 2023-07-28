package com.example.mybatisflex;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.mybatisflex.core.BaseMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@MapperScan(markerInterface = BaseMapper.class, annotationClass = Mapper.class)
public class MybatisFlexApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MybatisFlexApplication.class, args);
		log.info("Get Bean Definition Count = {}", context.getBeanDefinitionCount());
//		java.util.Arrays.stream(context.getBeanDefinitionNames()).forEach(System.err::println);
	}

}
