package com.cs590.lab4.client;

import com.cs590.lab4.client.dto.AddToCartRequest;
import com.cs590.lab4.client.dto.ProductDto;
import com.cs590.lab4.client.dto.ShoppingCartDto;
import com.cs590.lab4.client.dto.UpdatePriceRequest;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Lab4ScenarioRunner implements CommandLineRunner {
    private final RestTemplate restTemplate;
    private final String productServiceUrl;
    private final String shoppingServiceUrl;

    public Lab4ScenarioRunner(
            RestTemplate restTemplate,
            @Value("${product.service.url}") String productServiceUrl,
            @Value("${shopping.service.url}") String shoppingServiceUrl) {
        this.restTemplate = restTemplate;
        this.productServiceUrl = productServiceUrl;
        this.shoppingServiceUrl = shoppingServiceUrl;
    }

    @Override
    public void run(String... args) {
        String productNumber = "P-100";

        ProductDto newProduct = new ProductDto(
                productNumber,
                "Laptop CS590",
                "Notebook for SA Lab4",
                new BigDecimal("1200.00"));

        System.out.println("1) Add product");
        ProductDto created = restTemplate.postForObject(
                productServiceUrl + "/products", newProduct, ProductDto.class);
        System.out.println(created);

        System.out.println("2) Get product by productNumber");
        ProductDto fetchedProduct = restTemplate.getForObject(
                productServiceUrl + "/products/" + productNumber, ProductDto.class);
        System.out.println(fetchedProduct);

        System.out.println("3) Add product to shopping cart");
        AddToCartRequest addRequest = new AddToCartRequest(productNumber, 2);
        ShoppingCartDto cartAfterAdd = restTemplate.postForObject(
                shoppingServiceUrl + "/shoppingcart/items", addRequest, ShoppingCartDto.class);
        System.out.println(cartAfterAdd);

        System.out.println("4) Get shopping cart");
        ShoppingCartDto cart = restTemplate.getForObject(
                shoppingServiceUrl + "/shoppingcart", ShoppingCartDto.class);
        System.out.println(cart);

        System.out.println("5) Update product price in product-service");
        restTemplate.put(
                productServiceUrl + "/products/" + productNumber + "/price",
                new UpdatePriceRequest(new BigDecimal("950.00")));

        System.out.println("6) Get shopping cart after price update");
        ShoppingCartDto updatedCart = restTemplate.getForObject(
                shoppingServiceUrl + "/shoppingcart", ShoppingCartDto.class);
        System.out.println(updatedCart);
    }
}
