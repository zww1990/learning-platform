package io.example.elastic;

import io.example.elastic.domain.ProductV2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;

@SpringBootTest
public class ElasticsearchTemplateTests {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void testCreateIndex() {
        try {
            System.err.println(this.elasticsearchTemplate.indexOps(ProductV2.class).create());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIndexDocument() {
        ProductV2 product = new ProductV2().setSku("bk-1").setName("City bike").setPrice(123.01);
        try {
            System.err.println(this.elasticsearchTemplate.save(product));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateDocument() {
        ProductV2 product = new ProductV2().setSku("bk-1").setName("City bike").setPrice(123.1);
        try {
            System.err.println(this.elasticsearchTemplate.update(product));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetDocument() {
        try {
            System.err.println(this.elasticsearchTemplate.get("bk-1", ProductV2.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSearchDocument() {
        try {
            System.err.println(this.elasticsearchTemplate.search(new CriteriaQuery(new Criteria("name").contains("bike")), ProductV2.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteDocument() {
        try {
            System.err.println(this.elasticsearchTemplate.delete("bk-1", ProductV2.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteIndex() {
        try {
            System.err.println(this.elasticsearchTemplate.indexOps(ProductV2.class).delete());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
