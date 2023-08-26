package io.example.kickstart;

import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Collection;
import java.util.Collections;

@SpringBootTest
public class KickstartExampleApplicationTests {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testContextLoads() {
        applicationContext.getBeansOfType(GraphQLServletContextBuilder.class).forEach((k, v) -> {
            System.err.println(k + " = " + v);
        });
        System.err.println(applicationContext.getEnvironment().getProperty("graphql.extended-scalars", Collection.class, Collections.emptySet()));
    }

}
