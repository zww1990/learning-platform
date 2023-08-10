package io.example.mongo;

import io.example.mongo.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
public class MongoTemplateTests {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testSave() {
        try {
            Product product = new Product().setSku("bk-1").setName("City bike").setPrice(123.4);
            System.err.println(this.mongoTemplate.save(product, "products"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGet() {
        try {
            System.err.println(this.mongoTemplate.findAll(Product.class, "products"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        try {
            this.mongoTemplate.dropCollection("products");
            System.err.println("done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
