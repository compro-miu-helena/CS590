package com.cs590.lab4.product.service;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String productNumber) {
        super("Product not found: " + productNumber);
    }
}
