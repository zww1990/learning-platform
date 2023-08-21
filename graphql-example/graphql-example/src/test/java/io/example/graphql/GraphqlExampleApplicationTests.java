package io.example.graphql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.graphql.server.WebGraphQlInterceptor;

@SpringBootTest
public class GraphqlExampleApplicationTests {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testContextLoads() {
        applicationContext.getBeansOfType(WebGraphQlInterceptor.class).forEach((k, v) -> {
            System.err.println(k + " = " + v);
        });
    }

}
