package com.example.dubbo.service.impl;

import java.time.LocalDate;

import org.apache.dubbo.config.annotation.DubboService;

import com.example.dubbo.service.api.HelloService;
import com.example.dubbo.service.domain.Hello;

import lombok.extern.slf4j.Slf4j;

/**
 * 示例接口实现类
 * 
 * @author zhang weiwei
 * @since 2023年8月2日,下午3:05:13
 */
@DubboService(version = "1.0.0")
@Slf4j
public class HelloServiceImpl implements HelloService {

	@Override
	public Hello get(String name) {
		log.info("提供者：get(): name = {}", name);
		return new Hello()//
				.setId(System.currentTimeMillis())//
				.setAge(18)//
				.setBirthday(LocalDate.now().minusYears(18))//
				.setName(name);
	}

}
