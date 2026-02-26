package com.cs590.lab4.product.repository;

import com.cs590.lab4.product.domain.Product;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findByProductNumber(String productNumber);
}
