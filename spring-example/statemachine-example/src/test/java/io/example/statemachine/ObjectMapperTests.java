package io.example.statemachine;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.example.statemachine.domain.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

public class ObjectMapperTests {
    @Test
    public void testObjectMapper() {
        try {
            ObjectMapper json = Jackson2ObjectMapperBuilder.json()
                    .serializationInclusion(JsonInclude.Include.NON_NULL)
                    .build();
            System.err.println(json.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(new Order()
                            .setCreateUser("张三")
                            .setUpdateUser("张三")
                            .setPrice(12.3)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
