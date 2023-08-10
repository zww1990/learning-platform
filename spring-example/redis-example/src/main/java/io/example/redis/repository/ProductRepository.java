package io.example.redis.repository;

import io.example.redis.domain.ProductV2;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

/**
 * Product Repository
 *
 * @author zww
 * @since 2023-08-09 18:12:27
 */
public interface ProductRepository extends KeyValueRepository<ProductV2, String> {
}
