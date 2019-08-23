package com.demo.config;

import java.util.Properties;
import java.util.stream.Stream;
import javax.sql.DataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import com.demo.config.AppProperties.JdbcProperties;
import com.github.pagehelper.PageInterceptor;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

@Configuration
@ComponentScan("com.demo")
@MapperScan({ "com.wiwj.bdm.mq.mapper", "com.wiwj.bdm.base.mapper", "com.wiwj.bdm.esb.mapper" })
public class SpringConfig {
	@Bean
	public DataSource dataSource(JdbcProperties props) throws Exception {
		PoolDataSource dataSource = PoolDataSourceFactory.getPoolDataSource();
		dataSource.setConnectionFactoryClassName(props.getDriverClass());
		dataSource.setURL(props.getUrl());
		dataSource.setUser(props.getUser());
		dataSource.setPassword(props.getPassword());
		dataSource.setInitialPoolSize(5);
		dataSource.setMinPoolSize(5);
		dataSource.setMaxPoolSize(10);
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
		props.put("supportMethodsArguments", "true");
		page.setProperties(props);
		bean.setPlugins(Stream.of(page).toArray(Interceptor[]::new));
		org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
		// 全局地开启或关闭配置文件中的所有映射器已经配置的任何缓存。
		config.setCacheEnabled(false);
		// 是否开启自动驼峰命名规则映射
		config.setMapUnderscoreToCamelCase(true);
		// 指定当结果集中值为 null 的时候是否调用映射对象的 setter（map 对象时为 put）方法，
		// 这对于有 Map.keySet() 依赖或 null 值初始化的时候是有用的。
		config.setCallSettersOnNulls(true);
		// 当返回行的所有列都是空时，MyBatis默认返回null。 当开启这个设置时，MyBatis会返回一个空实例。
		config.setReturnInstanceForEmptyRow(true);
		// 当没有为参数提供特定的 JDBC 类型时，为空值指定 JDBC 类型。
		config.setJdbcTypeForNull(JdbcType.NULL);
		bean.setConfiguration(config);
		return bean.getObject();
	}
}
