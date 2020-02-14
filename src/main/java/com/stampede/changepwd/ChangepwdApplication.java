package com.stampede.changepwd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.boot.autoconfigure.ldap.LdapProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.pool2.factory.PoolConfig;
import org.springframework.ldap.pool2.factory.PooledContextSource;
import org.springframework.ldap.pool2.validation.DefaultDirContextValidator;
import org.springframework.ldap.transaction.compensating.manager.TransactionAwareContextSourceProxy;

@SpringBootApplication(exclude = { TaskExecutionAutoConfiguration.class, TaskSchedulingAutoConfiguration.class,
		PersistenceExceptionTranslationAutoConfiguration.class, SpringDataWebAutoConfiguration.class,
		OAuth2ResourceServerAutoConfiguration.class, ProjectInfoAutoConfiguration.class })
public class ChangepwdApplication {
	private static final Logger log = LoggerFactory.getLogger(ChangepwdApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ChangepwdApplication.class, args);
		log.info("工厂中定义的bean数量={}", context.getBeanDefinitionCount());
//		java.util.Arrays.stream(context.getBeanDefinitionNames()).forEach(System.err::println);
	}

	@Bean
	public ContextSource contextSource(LdapProperties properties) {
		LdapContextSource ldapContextSource = new LdapContextSource();
		ldapContextSource.setUrls(properties.getUrls());
		ldapContextSource.setBase(properties.getBase());
		ldapContextSource.setUserDn(properties.getUsername());
		ldapContextSource.setPassword(properties.getPassword());
		ldapContextSource.afterPropertiesSet();
		PoolConfig poolConfig = new PoolConfig();
		PooledContextSource pooledContextSource = new PooledContextSource(poolConfig);
		pooledContextSource.setContextSource(ldapContextSource);
		pooledContextSource.setDirContextValidator(new DefaultDirContextValidator());
		TransactionAwareContextSourceProxy proxy = new TransactionAwareContextSourceProxy(pooledContextSource);
		return proxy;
	}

	@Bean
	public LdapTemplate ldapTemplate(ContextSource contextSource) {
		return new LdapTemplate(contextSource);
	}
}
