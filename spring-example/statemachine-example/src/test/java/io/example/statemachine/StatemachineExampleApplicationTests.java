package io.example.statemachine;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class StatemachineExampleApplicationTests {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testContextLoads() {
        this.applicationContext.getBeansOfType(RedisTemplate.class).forEach((k, v) -> {
            System.err.println("************************" + k + "=" + v + "**************************");
            System.err.println(v.getDefaultSerializer());
            System.err.println(v.getKeySerializer());
            System.err.println(v.getValueSerializer());
            System.err.println(v.getHashKeySerializer());
            System.err.println(v.getHashValueSerializer());
        });
    }

}
