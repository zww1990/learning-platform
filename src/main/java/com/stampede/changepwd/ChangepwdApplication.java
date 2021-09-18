package com.stampede.changepwd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.stampede.changepwd.service.PersonJobService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ZhangWeiWei
 * @date 2020年2月17日,下午1:50:33
 * @description 程序启动类
 */
@SpringBootApplication(exclude = { //
		PersistenceExceptionTranslationAutoConfiguration.class, //
		SpringDataWebAutoConfiguration.class, //
		OAuth2ResourceServerAutoConfiguration.class, //
		ProjectInfoAutoConfiguration.class, //
		WebSocketServletAutoConfiguration.class, //
		RestTemplateAutoConfiguration.class })
@ConfigurationPropertiesScan
@EnableScheduling
@Slf4j
public class ChangepwdApplication implements WebMvcConfigurer {

	/**
	 * 程序主入口
	 * 
	 * @author ZhangWeiWei
	 * @date 2020年2月17日,下午1:49:50
	 * @param args 启动参数
	 */
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ChangepwdApplication.class, args);
		log.info("工厂中定义的bean数量={}", context.getBeanDefinitionCount());
//		java.util.Arrays.stream(context.getBeanDefinitionNames()).forEach(System.err::println);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this.loginHandlerInterceptor())//
				.addPathPatterns("/admin/**");
	}

	@Bean
	public LoginHandlerInterceptor loginHandlerInterceptor() {
		return new LoginHandlerInterceptor();
	}

	@Bean
	public PersonJobService personJobService() {
		return new PersonJobService();
	}
}
