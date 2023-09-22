package io.example.c3p0.springboot.autoconfigure;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import io.example.c3p0.springboot.autoconfigure.properties.C3p0DataSourceProperties;
import lombok.extern.slf4j.Slf4j;

@Configuration
@ConditionalOnClass(ComboPooledDataSource.class)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@EnableConfigurationProperties({ C3p0DataSourceProperties.class })
@Slf4j
public class C3p0DataSourceAutoConfigure {
	@Bean(destroyMethod = "close")
	@ConditionalOnMissingBean
	DataSource dataSource(C3p0DataSourceProperties properties) throws Exception {
		log.info("初始化[c3p0]数据源");
		ComboPooledDataSource source = new ComboPooledDataSource();
		PropertyMapper mapper = PropertyMapper.get();
		mapper.from(properties::getDriverClass).whenHasText().to(t -> this.setDriverClass(source, t));
		mapper.from(properties::getJdbcUrl).whenHasText().to(source::setJdbcUrl);
		mapper.from(properties::getUser).whenHasText().to(source::setUser);
		mapper.from(properties::getPassword).whenHasText().to(source::setPassword);
		mapper.from(properties::getAcquireIncrement).whenNonNull().to(source::setAcquireIncrement);
		mapper.from(properties::getAcquireRetryAttempts).whenNonNull().to(source::setAcquireRetryAttempts);
		mapper.from(properties::getAcquireRetryDelay).whenNonNull().to(source::setAcquireRetryDelay);
		mapper.from(properties::getAutoCommitOnClose).whenNonNull().to(source::setAutoCommitOnClose);
		mapper.from(properties::getAutomaticTestTable).whenHasText().to(source::setAutomaticTestTable);
		mapper.from(properties::getBreakAfterAcquireFailure).whenNonNull().to(source::setBreakAfterAcquireFailure);
		mapper.from(properties::getCheckoutTimeout).whenNonNull().to(source::setCheckoutTimeout);
		mapper.from(properties::getConnectionCustomizerClassName).whenHasText()
				.to(source::setConnectionCustomizerClassName);
		mapper.from(properties::getConnectionTesterClassName).whenHasText()
				.to(t -> this.setConnectionTesterClassName(source, t));
		mapper.from(properties::getContextClassLoaderSource).whenHasText()
				.to(t -> this.setContextClassLoaderSource(source, t));
		mapper.from(properties::getDebugUnreturnedConnectionStackTraces).whenNonNull()
				.to(source::setDebugUnreturnedConnectionStackTraces);
		mapper.from(properties::getDescription).whenHasText().to(source::setDescription);
		mapper.from(properties::getForceIgnoreUnresolvedTransactions).whenNonNull()
				.to(source::setForceIgnoreUnresolvedTransactions);
		mapper.from(properties::getForceSynchronousCheckins).whenNonNull().to(source::setForceSynchronousCheckins);
		mapper.from(properties::getForceUseNamedDriverClass).whenNonNull().to(source::setForceUseNamedDriverClass);
		mapper.from(properties::getIdleConnectionTestPeriod).whenNonNull().to(source::setIdleConnectionTestPeriod);
		mapper.from(properties::getInitialPoolSize).whenNonNull().to(source::setInitialPoolSize);
		mapper.from(properties::getMaxAdministrativeTaskTime).whenNonNull().to(source::setMaxAdministrativeTaskTime);
		mapper.from(properties::getMaxConnectionAge).whenNonNull().to(source::setMaxConnectionAge);
		mapper.from(properties::getMaxIdleTime).whenNonNull().to(source::setMaxIdleTime);
		mapper.from(properties::getMaxIdleTimeExcessConnections).whenNonNull()
				.to(source::setMaxIdleTimeExcessConnections);
		mapper.from(properties::getMaxPoolSize).whenNonNull().to(source::setMaxPoolSize);
		mapper.from(properties::getMaxStatements).whenNonNull().to(source::setMaxStatements);
		mapper.from(properties::getMaxStatementsPerConnection).whenNonNull().to(source::setMaxStatementsPerConnection);
		mapper.from(properties::getMinPoolSize).whenNonNull().to(source::setMinPoolSize);
		mapper.from(properties::getOverrideDefaultPassword).whenHasText().to(source::setOverrideDefaultPassword);
		mapper.from(properties::getOverrideDefaultUser).whenHasText().to(source::setOverrideDefaultUser);
		mapper.from(properties::getPreferredTestQuery).whenHasText().to(source::setPreferredTestQuery);
		mapper.from(properties::getPrivilegeSpawnedThreads).whenNonNull().to(source::setPrivilegeSpawnedThreads);
		mapper.from(properties::getPropertyCycle).whenNonNull().to(source::setPropertyCycle);
		mapper.from(properties::getStatementCacheNumDeferredCloseThreads).whenNonNull()
				.to(source::setStatementCacheNumDeferredCloseThreads);
		mapper.from(properties::getTestConnectionOnCheckin).whenNonNull().to(source::setTestConnectionOnCheckin);
		mapper.from(properties::getTestConnectionOnCheckout).whenNonNull().to(source::setTestConnectionOnCheckout);
		mapper.from(properties::getUnreturnedConnectionTimeout).whenNonNull()
				.to(source::setUnreturnedConnectionTimeout);
		mapper.from(properties::getUserOverridesAsString).whenHasText()
				.to(t -> this.setUserOverridesAsString(source, t));
		mapper.from(properties::getUsesTraditionalReflectiveProxies).whenNonNull()
				.to(source::setUsesTraditionalReflectiveProxies);
		return source;
	}

	private void setDriverClass(ComboPooledDataSource source, String driverClass) {
		try {
			source.setDriverClass(driverClass);
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}
	}

	private void setConnectionTesterClassName(ComboPooledDataSource source, String className) {
		try {
			source.setConnectionTesterClassName(className);
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}
	}

	private void setContextClassLoaderSource(ComboPooledDataSource source, String loaderSource) {
		try {
			source.setContextClassLoaderSource(loaderSource);
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}
	}

	private void setUserOverridesAsString(ComboPooledDataSource source, String overridesAsString) {
		try {
			source.setUserOverridesAsString(overridesAsString);
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}
	}
}
