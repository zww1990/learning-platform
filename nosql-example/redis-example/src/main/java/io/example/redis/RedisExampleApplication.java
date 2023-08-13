package io.example.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Redis Example Application
 *
 * @author zww
 * @since 2023-08-09 14:50:27
 */
@SpringBootApplication
@Slf4j
@SuppressWarnings({"rawtypes", "unchecked"})
public class RedisExampleApplication implements CommandLineRunner {
    private final RedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    public RedisExampleApplication(RedisTemplate redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RedisExampleApplication.class, args);
        log.info("当前容器中的bean总数={}", context.getBeanDefinitionCount());
//        java.util.stream.Stream.of(context.getBeanDefinitionNames()).forEach(System.err::println);
    }

    @Override
    public void run(String... args) throws Exception {
        RedisSerializer<Object> redisSerializer = new GenericJackson2JsonRedisSerializer(this.objectMapper);
        this.redisTemplate.setKeySerializer(this.redisTemplate.getStringSerializer());
        this.redisTemplate.setHashKeySerializer(this.redisTemplate.getStringSerializer());
        this.redisTemplate.setValueSerializer(redisSerializer);
        this.redisTemplate.setHashValueSerializer(redisSerializer);
    }

}
