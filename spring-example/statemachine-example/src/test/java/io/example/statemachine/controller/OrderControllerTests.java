package io.example.statemachine.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.example.statemachine.domain.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderControllerTests {
    @Autowired
    private OrderController orderController;
    @Autowired
    private ObjectMapper json;

    @Test
    public void testGet() {
        try {
            System.err.println(json.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(orderController.get(7L)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreate() {
        try {
            System.err.println(json.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(orderController.create(new Order()
                            .setCreateUser("张三")
                            .setPrice(12.3))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
