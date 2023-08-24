package io.example.graphql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import org.springframework.graphql.server.WebGraphQlInterceptor;

@SpringBootTest
public class GraphqlExampleApplicationTests {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testContextLoads() {
        System.err.println("----------------WebGraphQlInterceptor--------------------");
        applicationContext.getBeansOfType(WebGraphQlInterceptor.class).forEach((k, v) -> {
            System.err.println(k + " = " + v);
        });
        System.err.println("----------------RuntimeWiringConfigurer--------------------");
        applicationContext.getBeansOfType(RuntimeWiringConfigurer.class).forEach((k, v) -> {
            System.err.println(k + " = " + v);
        });
        System.err.println("----------------GraphQlSourceBuilderCustomizer--------------------");
        applicationContext.getBeansOfType(GraphQlSourceBuilderCustomizer.class).forEach((k, v) -> {
            System.err.println(k + " = " + v);
        });
    }

}
