package com.example.dubbo.config;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.DefaultLettucePool;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.util.StringUtils;

import com.example.dubbo.config.RedisConfig.RedisCondition;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

@Conditional(RedisCondition.class)
public class RedisConfig {
	@Bean
	public RedisConnectionFactory redisConnectionFactory(RedisProperties props) {
		DefaultLettucePool lettuce = null;
		if (StringUtils.hasText(props.getHost()) && props.getPort() > 0) {
			lettuce = new DefaultLettucePool(props.getHost(), props.getPort());
		} else {
			RedisSentinelConfiguration sentinel = new RedisSentinelConfiguration().master(props.getSentinelMaster());
			for (String node : props.getSentinelNodes()) {
				String[] hostAndPort = StringUtils.split(node, ":");
				sentinel.sentinel(hostAndPort[0], Integer.parseInt(hostAndPort[1]));
			}
			lettuce = new DefaultLettucePool(sentinel);
		}
		lettuce.setDatabase(props.getDatabase());
		lettuce.setPassword(props.getPassword());
		GenericObjectPoolConfig pool = new GenericObjectPoolConfig();
		pool.setMaxTotal(props.getMaxActive());
		pool.setMaxIdle(props.getMaxIdle());
		pool.setMinIdle(props.getMinIdle());
		pool.setMaxWaitMillis(props.getMaxWait());
		lettuce.setPoolConfig(pool);
		lettuce.afterPropertiesSet();
		return new LettuceConnectionFactory(lettuce);
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		template.setEnableDefaultSerializer(false);
		template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
		template.setKeySerializer(template.getStringSerializer());
		template.setHashKeySerializer(template.getStringSerializer());
		template.setValueSerializer(template.getDefaultSerializer());
		template.setHashValueSerializer(template.getDefaultSerializer());
		return template;
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper()
				.registerModule(new ParameterNamesModule())
				.registerModule(new Jdk8Module())
				.registerModule(new JavaTimeModule())
				.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
				.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
	}

	public static class RedisCondition implements Condition {
		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			return context.getEnvironment().getProperty("redis.enable", boolean.class, false);
		}
	}
}
