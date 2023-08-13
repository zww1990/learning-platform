package io.example.elastic.repository;

import io.example.elastic.domain.ProductV3;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Product Repository
 *
 * @author zww
 * @since 2023-08-09 18:12:27
 */
public interface ProductRepository extends ElasticsearchRepository<ProductV3, String> {
}
