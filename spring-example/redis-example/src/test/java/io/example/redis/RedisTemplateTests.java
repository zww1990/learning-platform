package io.example.redis;

import io.example.redis.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@SpringBootTest
@SuppressWarnings({"rawtypes", "unchecked"})
public class RedisTemplateTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testSave() {
        try {
            Product product = new Product().setSku("bk-1").setName("City bike").setPrice(123.4);
            BoundValueOperations<String, List<Product>> valueOps = this.redisTemplate.boundValueOps("products");
            valueOps.set(List.of(product, product));
            System.err.println(valueOps.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGet() {
        try {
            BoundValueOperations<String, List<Product>> valueOps = this.redisTemplate.boundValueOps("products");
            System.err.println(valueOps.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        try {
            System.err.println(this.redisTemplate.delete("products"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
