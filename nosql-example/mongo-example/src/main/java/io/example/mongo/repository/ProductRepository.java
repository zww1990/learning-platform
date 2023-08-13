package io.example.mongo.repository;

import io.example.mongo.domain.ProductV2;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Product Repository
 *
 * @author zww
 * @since 2023-08-09 18:12:27
 */
public interface ProductRepository extends MongoRepository<ProductV2, String> {
}
