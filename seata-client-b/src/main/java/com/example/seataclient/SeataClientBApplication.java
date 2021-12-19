package com.example.seataclient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import com.alibaba.cloud.seata.rest.SeataRestTemplateAutoConfiguration;
import com.alibaba.cloud.seata.rest.SeataRestTemplateInterceptor;

/**
 * 客户端B启动类
 * 
 * @author zww1990@foxmail.com
 * @since 2021年12月19日,下午4:29:41
 */
@EnableFeignClients // 激活 @FeignClient
@EnableDiscoveryClient
@SpringBootApplication(exclude = { //
		SeataRestTemplateAutoConfiguration.class,// 官方提供的自动配置类会出现bean循环引用
})
@MapperScan(annotationClass = Mapper.class)
public class SeataClientBApplication implements CommandLineRunner {

	/**
	 * 主方法
	 * 
	 * @author zww1990@foxmail.com
	 * @since 2021年12月19日,下午4:36:58
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SeataClientBApplication.class, args);
	}

	/**
	 * seata REST模板请求拦截器
	 * 
	 * @author zww1990@foxmail.com
	 * @since 2021年12月19日,下午4:37:32
	 * @return
	 */
	@Bean
	public SeataRestTemplateInterceptor seataRestTemplateInterceptor() {
		SeataRestTemplateInterceptor interceptor = new SeataRestTemplateInterceptor();
		return interceptor;
	}

	@Autowired(required = false)
	private Collection<RestTemplate> restTemplates;

	@Override
	public void run(String... args) throws Exception {
		if (this.restTemplates != null) {
			ClientHttpRequestInterceptor interceptor = this.seataRestTemplateInterceptor();
			for (RestTemplate restTemplate : restTemplates) {
				List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>(restTemplate.getInterceptors());
				interceptors.add(interceptor);
				restTemplate.setInterceptors(interceptors);
			}
		}
	}

}
