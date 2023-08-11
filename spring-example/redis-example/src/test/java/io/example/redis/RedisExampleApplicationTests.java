package io.example.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.keyvalue.core.KeyValueTemplate;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisExampleApplicationTests {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    @SuppressWarnings("rawtypes")
    public void testContextLoads() {
        RedisTemplate redisTemplate = this.applicationContext.getBean("redisTemplate", RedisTemplate.class);
        System.err.println("default: " + redisTemplate.getDefaultSerializer());
        System.err.println("string: " + redisTemplate.getStringSerializer());
        System.err.println("key: " + redisTemplate.getKeySerializer());
        System.err.println("value: " + redisTemplate.getValueSerializer());
        System.err.println("hash key: " + redisTemplate.getHashKeySerializer());
        System.err.println("hash value: " + redisTemplate.getHashValueSerializer());
        System.out.println("------------------------------------------------------------------------------------");
        KeyValueTemplate keyValueTemplate = this.applicationContext.getBean(KeyValueTemplate.class);
        System.err.println("keyValueTemplate = " + keyValueTemplate);
        RedisKeyValueTemplate redisKeyValueTemplate = this.applicationContext.getBean(RedisKeyValueTemplate.class);
        System.err.println("redisKeyValueTemplate = " + redisKeyValueTemplate);
    }

}
