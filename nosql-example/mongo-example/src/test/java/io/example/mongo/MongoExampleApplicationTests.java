package io.example.mongo;

import com.mongodb.client.MongoClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
public class MongoExampleApplicationTests {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testContextLoads() {
        MongoTemplate mongoTemplate = this.applicationContext.getBean(MongoTemplate.class);
        System.err.println(mongoTemplate);
        MongoClient mongoClient = this.applicationContext.getBean(MongoClient.class);
        System.err.println(mongoClient);
    }

}
