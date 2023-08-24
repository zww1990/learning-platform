package io.example.dgs;

import com.netflix.graphql.dgs.mvc.DgsGraphQLRequestHeaderValidator;
import com.netflix.graphql.dgs.mvc.GraphQLRequestHeaderValidationRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Map;

@SpringBootTest
public class DgsExampleApplicationTests {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testContextLoads() {
        DgsGraphQLRequestHeaderValidator validator = this.applicationContext.getBean(DgsGraphQLRequestHeaderValidator.class);
        Map<String, GraphQLRequestHeaderValidationRule> ruleMap = this.applicationContext.getBeansOfType(GraphQLRequestHeaderValidationRule.class);
        System.err.println(validator);
        System.err.println(ruleMap);
    }

}
