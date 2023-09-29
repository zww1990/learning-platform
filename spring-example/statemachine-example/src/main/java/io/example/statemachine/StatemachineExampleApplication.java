package io.example.statemachine;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 状态机示例应用程序启动类
 *
 * @author 张维维
 * @since 2023-09-29 18:13:58
 */
@SpringBootApplication
@Slf4j
@AllArgsConstructor
@MapperScan(annotationClass = Mapper.class)
public class StatemachineExampleApplication implements CommandLineRunner {
    private final RedisTemplate<Object, Object> redisTemplate;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(StatemachineExampleApplication.class, args);
        log.info("工厂中定义的bean数量 = {}", context.getBeanDefinitionCount());
//		java.util.stream.Stream.of(context.getBeanDefinitionNames()).forEach(System.err::println);
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
