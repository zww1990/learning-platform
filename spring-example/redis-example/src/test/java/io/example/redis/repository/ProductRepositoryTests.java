package io.example.redis.repository;

import io.example.redis.domain.ProductV2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductRepositoryTests {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSave() {
        try {
            System.err.println(this.productRepository.save(new ProductV2().setSku("bk-1").setName("City bike").setPrice(123.01)));
            System.err.println(this.productRepository.save(new ProductV2().setSku("bk-2").setName("City bike 2").setPrice(123.02)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGet() {
        try {
            System.err.println(this.productRepository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        try {
            this.productRepository.deleteAll();
            System.err.println("done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
