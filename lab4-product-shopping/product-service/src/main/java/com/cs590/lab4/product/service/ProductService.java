package com.cs590.lab4.product.service;

import com.cs590.lab4.product.domain.Product;
import com.cs590.lab4.product.repository.ProductRepository;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProduct(String productNumber) {
        return productRepository.findByProductNumber(productNumber)
                .orElseThrow(() -> new ProductNotFoundException(productNumber));
    }

    public Product updateProductPrice(String productNumber, BigDecimal newPrice) {
        Product product = getProduct(productNumber);
        product.setPrice(newPrice);
        return productRepository.save(product);
    }
}
