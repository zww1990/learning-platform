package io.example.redis;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Redis Example Application
 *
 * @author zww
 * @since 2023-08-09 14:50:27
 */
@SpringBootApplication
@Slf4j
@AllArgsConstructor
public class RedisExampleApplication implements CommandLineRunner {
    private final RedisTemplate<Object, Object> redisTemplate;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RedisExampleApplication.class, args);
        log.info("当前容器中的bean总数={}", context.getBeanDefinitionCount());
//        java.util.stream.Stream.of(context.getBeanDefinitionNames()).forEach(System.err::println);
    }

    @Override
    public void run(String... args) throws Exception {
        RedisSerializer<Object> redisSerializer = RedisSerializer.json();
        this.redisTemplate.setKeySerializer(this.redisTemplate.getStringSerializer());
        this.redisTemplate.setHashKeySerializer(this.redisTemplate.getStringSerializer());
        this.redisTemplate.setValueSerializer(redisSerializer);
        this.redisTemplate.setHashValueSerializer(redisSerializer);
    }

}
