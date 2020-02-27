package com.stampede.changepwd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ZhangWeiWei
 * @date 2020年2月17日,下午1:50:33
 * @description 程序启动类
 */
@SpringBootApplication(exclude = { TaskExecutionAutoConfiguration.class, TaskSchedulingAutoConfiguration.class,
		PersistenceExceptionTranslationAutoConfiguration.class, SpringDataWebAutoConfiguration.class,
		OAuth2ResourceServerAutoConfiguration.class, ProjectInfoAutoConfiguration.class })
@EnableConfigurationProperties(ChangepwdProperties.class)
public class ChangepwdApplication extends SpringBootServletInitializer implements WebMvcConfigurer {
	private static final Logger log = LoggerFactory.getLogger(ChangepwdApplication.class);

	/**
	 * 程序主入口
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
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ChangepwdApplication.class);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this.loginHandlerInterceptor()).addPathPatterns("/admin/**");
	}

	@Bean
	public LoginHandlerInterceptor loginHandlerInterceptor() {
		return new LoginHandlerInterceptor();
	}
}
