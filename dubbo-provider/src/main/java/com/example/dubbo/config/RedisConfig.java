package com.example.dubbo.config;

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
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import com.example.dubbo.config.RedisConfig.RedisCondition;

@Conditional(RedisCondition.class)
public class RedisConfig {
	@Bean
	public RedisConnectionFactory redisConnectionFactory(RedisProperties props) {
		RedisSentinelConfiguration sentinel = new RedisSentinelConfiguration().master(props.getSentinelMaster());
		for (String node : props.getSentinelNodes()) {
			String[] hostAndPort = StringUtils.split(node, ":");
			sentinel.sentinel(hostAndPort[0], Integer.parseInt(hostAndPort[1]));
		}
		DefaultLettucePool lettuce = new DefaultLettucePool(sentinel);
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
	public StringRedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		return new StringRedisTemplate(redisConnectionFactory);
	}

	public static class RedisCondition implements Condition {
		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			return context.getEnvironment().getProperty("redis.enable", boolean.class, false);
		}
	}
}
