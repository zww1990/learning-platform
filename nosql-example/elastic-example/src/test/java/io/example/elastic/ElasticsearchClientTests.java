package io.example.elastic;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import io.example.elastic.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ElasticsearchClientTests {
    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Test
    public void testCreateIndex() {
        try {
            System.err.println(this.elasticsearchClient.indices().create(c -> c.index("products")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIndexDocument() {
        Product product = new Product().setSku("bk-1").setName("City bike").setPrice(123.0);
        try {
            System.err.println(this.elasticsearchClient.index(i -> i.index("products").id(product.getSku()).document(product)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateDocument() {
        Product product = new Product().setSku("bk-1").setName("City bike").setPrice(123.1);
        try {
            System.err.println(this.elasticsearchClient.update(u -> u.index("products").id(product.getSku()).doc(product), Product.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetDocument() {
        try {
            System.err.println(this.elasticsearchClient.get(g -> g.index("products").id("bk-1"), Product.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSearchDocument() {
        try {
            System.err.println(this.elasticsearchClient.search(s -> s.index("products").query(q -> q.match(t -> t.field("name").query("bike"))), Product.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteDocument() {
        try {
            System.err.println(this.elasticsearchClient.delete(d -> d.index("products").id("bk-1")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteIndex() {
        try {
            System.err.println(this.elasticsearchClient.indices().delete(d -> d.index("products")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
