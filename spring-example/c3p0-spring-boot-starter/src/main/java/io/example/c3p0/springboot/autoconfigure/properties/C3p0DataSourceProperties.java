package io.example.c3p0.springboot.autoconfigure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ConfigurationProperties(prefix = "spring.datasource.c3p0")
@Getter
@Setter
@ToString
public class C3p0DataSourceProperties {
	private Integer acquireIncrement;
	private Integer acquireRetryAttempts;
	private Integer acquireRetryDelay;
	private Boolean autoCommitOnClose;
	private String automaticTestTable;
	private Boolean breakAfterAcquireFailure;
	private Integer checkoutTimeout;
	private String connectionCustomizerClassName;
	private String connectionTesterClassName;
	private String contextClassLoaderSource;
	private Boolean debugUnreturnedConnectionStackTraces;
	private String description;
	private String driverClass;
	private Boolean forceIgnoreUnresolvedTransactions;
	private Boolean forceSynchronousCheckins;
	private Boolean forceUseNamedDriverClass;
	private Integer idleConnectionTestPeriod;
	private Integer initialPoolSize;
	private String jdbcUrl;
	private Integer maxAdministrativeTaskTime;
	private Integer maxConnectionAge;
	private Integer maxIdleTime;
	private Integer maxIdleTimeExcessConnections;
	private Integer maxPoolSize;
	private Integer maxStatements;
	private Integer maxStatementsPerConnection;
	private Integer minPoolSize;
	private String overrideDefaultPassword;
	private String overrideDefaultUser;
	private String password;
	private String preferredTestQuery;
	private Boolean privilegeSpawnedThreads;
	private Integer propertyCycle;
	private Integer statementCacheNumDeferredCloseThreads;
	private Boolean testConnectionOnCheckin;
	private Boolean testConnectionOnCheckout;
	private Integer unreturnedConnectionTimeout;
	private String user;
	private String userOverridesAsString;
	private Boolean usesTraditionalReflectiveProxies;
}
