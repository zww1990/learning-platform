package com.demo.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.demo.config.AppProperties.DubboProperties;
import com.demo.config.AppProperties.JdbcProperties;
import com.demo.service.JobService;
import com.github.pagehelper.PageInterceptor;

@Configuration
@ComponentScan("com.demo")
@DubboComponentScan("com.demo.service.impl")
@MapperScan({ "com.wiwj.bdm.mq.mapper", "com.wiwj.bdm.base.mapper" })
public class SpringConfig {
	@Bean
	public ApplicationConfig applicationConfig(DubboProperties props) {
		ApplicationConfig config = new ApplicationConfig();
		config.setName(props.getAppName());
		return config;
	}

	@Bean
	public RegistryConfig registryConfig(DubboProperties props) {
		RegistryConfig config = new RegistryConfig();
		config.setProtocol("zookeeper");
		config.setAddress(props.getRegistryAddress());
		return config;
	}

	@Bean
	public DataSource dataSource(JdbcProperties props) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(props.getDriverClass());
		dataSource.setUrl(props.getUrl());
		dataSource.setUsername(props.getUser());
		dataSource.setPassword(props.getPassword());
		return dataSource;
	}

	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		PageInterceptor page = new PageInterceptor();
		Properties props = new Properties();
		props.put("supportMethodsArguments", true);
		page.setProperties(props);
		bean.setPlugins(ArrayUtils.toArray(page));
		org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
		config.setCacheEnabled(false);
		config.setMapUnderscoreToCamelCase(true);
		config.setCallSettersOnNulls(true);
		config.setReturnInstanceForEmptyRow(true);
		bean.setConfiguration(config);
		return bean.getObject();
	}

	@Bean(initMethod = "init", destroyMethod = "close")
	public ZookeeperRegistryCenter zookeeperRegistryCenter(DubboProperties props) {
		return new ZookeeperRegistryCenter(new ZookeeperConfiguration(props.getRegistryAddress(), props.getAppName()));
	}

	@Bean(initMethod = "init")
	public SpringJobScheduler springJobScheduler(JobService jobService, ZookeeperRegistryCenter regCenter) {
		String jobName = jobService.getClass().getName();
		return new SpringJobScheduler(jobService, regCenter,
				LiteJobConfiguration
						.newBuilder(new SimpleJobConfiguration(
								JobCoreConfiguration.newBuilder(jobName, "0 0/1 * * * ?", 1).build(), jobName))
						.overwrite(true).build());
	}

	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl ms = new JavaMailSenderImpl();
		ms.setDefaultEncoding("UTF-8");
		ms.setProtocol("smtp");
		ms.setHost("smtp.sohu.com");
		ms.setPort(25);
		ms.setUsername("nokia0561861@sohu.com");
		ms.setPassword("zhangWW@1021");
		return ms;
	}
}
