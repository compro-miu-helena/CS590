package com.cs590.lab4.shopping.repository;

import com.cs590.lab4.shopping.domain.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {
}
