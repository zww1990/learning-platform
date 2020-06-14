package com.example.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Spring Security应用启动类
 * 
 * @author home
 */
@SpringBootApplication(exclude = { //
		TaskExecutionAutoConfiguration.class, // 任务执行自动配置
		TaskSchedulingAutoConfiguration.class, // 任务计划自动配置
		ProjectInfoAutoConfiguration.class, // 项目信息自动配置
		ValidationAutoConfiguration.class, // 验证自动配置
		AopAutoConfiguration.class, // AOP自动配置
		MultipartAutoConfiguration.class, // 分段上传自动配置
		PersistenceExceptionTranslationAutoConfiguration.class, // 持久化异常转换自动配置
		RestTemplateAutoConfiguration.class, // REST模板自动配置
		JdbcTemplateAutoConfiguration.class, // JDBC模板自动配置
		WebSocketServletAutoConfiguration.class, // WebSocket自动配置
		SpringDataWebAutoConfiguration.class,// Spring Data Web自动配置
})
public class SpringSecurityApplication {
	private static final Logger log = LoggerFactory.getLogger(SpringSecurityApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringSecurityApplication.class, args);
		log.info("应用程序上下文Bean定义计数={}", context.getBeanDefinitionCount());
//		java.util.Arrays.stream(context.getBeanDefinitionNames()).forEach(System.err::println);
	}

}
