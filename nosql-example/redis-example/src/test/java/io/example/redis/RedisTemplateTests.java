package io.example.redis;

import io.example.redis.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;
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
            valueOps.set(Arrays.asList(product, product));
            System.err.println(valueOps.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGet() {
        try {
            BoundValueOperations<String, List<Product>> valueOps = this.redisTemplate.boundValueOps("products");
            valueOps.get().forEach(System.err::println);
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
