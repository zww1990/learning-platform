package io.example.elastic;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.elasticsearch.client.RestClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;

@SpringBootTest
public class ElasticExampleApplicationTests {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testContextLoads() {
        RestClient restClient = this.applicationContext.getBean(RestClient.class);
        ElasticsearchTemplate elasticsearchTemplate = this.applicationContext.getBean(ElasticsearchTemplate.class);
        ElasticsearchClient elasticsearchClient = this.applicationContext.getBean(ElasticsearchClient.class);
        System.err.println(restClient);
        System.err.println(elasticsearchTemplate);
        System.err.println(elasticsearchClient);
    }

}
