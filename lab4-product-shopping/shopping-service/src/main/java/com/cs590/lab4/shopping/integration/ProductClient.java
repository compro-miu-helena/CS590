package com.cs590.lab4.shopping.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductClient {
    private final RestTemplate restTemplate;
    private final String productServiceBaseUrl;

    public ProductClient(RestTemplate restTemplate,
                         @Value("${product.service.base-url}") String productServiceBaseUrl) {
        this.restTemplate = restTemplate;
        this.productServiceBaseUrl = productServiceBaseUrl;
    }

    public ProductResponse getProductByNumber(String productNumber) {
        String url = productServiceBaseUrl + "/products/" + productNumber;
        try {
            return restTemplate.getForObject(url, ProductResponse.class);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new ProductServiceException("Product not found in product-service: " + productNumber, ex);
        }
    }
}
