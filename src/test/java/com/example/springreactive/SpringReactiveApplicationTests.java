package com.example.springreactive;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.client.GetAliasesResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.cluster.metadata.AliasMetadata;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * SpringReactiveApplicationTests
 * 
 * @author weiwei
 * @version v1
 * @since 2022年4月26日,下午5:53:42
 */
@SpringBootTest
@AutoConfigureWebTestClient
@SuppressWarnings("deprecation")
public class SpringReactiveApplicationTests {
	@Resource
	private WebTestClient webClient;
	@Resource
	private R2dbcEntityTemplate entityTemplate;
	@Resource
	private RestHighLevelClient highLevelClient;

	@Test
	public void contextLoads() {
		System.err.println(this.webClient);
		System.err.println(this.entityTemplate);
		System.err.println(this.highLevelClient);
	}

	@Test
	public void testGetAllIndices() {
		try {
			GetAliasesRequest request = new GetAliasesRequest();
			GetAliasesResponse response = this.highLevelClient.indices().getAlias(request, RequestOptions.DEFAULT);
			Map<String, Set<AliasMetadata>> map = response.getAliases();
			Set<String> indices = map.keySet();
			for (String key : indices) {
				System.out.println(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
