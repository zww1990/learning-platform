package com.demo.config;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.github.pagehelper.PageInterceptor;

@Configuration
@ComponentScan("com.demo")
@DubboComponentScan("com.demo.service.impl")
@MapperScan("com.wiwj.bdm.mq.mapper")
public class SpringConfig {
	@Bean
	public ApplicationConfig applicationConfig(AppProperties props) {
		ApplicationConfig config = new ApplicationConfig();
		config.setName(props.getAppName());
		return config;
	}

	@Bean
	public RegistryConfig registryConfig(AppProperties props) {
		RegistryConfig config = new RegistryConfig();
		config.setAddress(props.getRegistryAddress());
		return config;
	}

	@Bean
	public DataSource dataSource(AppProperties props) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(props.getJdbcDriverClass());
		dataSource.setUrl(props.getJdbcUrl());
		dataSource.setUsername(props.getJdbcUser());
		dataSource.setPassword(props.getJdbcPassword());
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
		bean.setPlugins(new Interceptor[] { new PageInterceptor() });
		return bean.getObject();
	}
}
