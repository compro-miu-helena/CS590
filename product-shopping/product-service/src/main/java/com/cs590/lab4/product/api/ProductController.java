package com.cs590.lab4.product.api;

import com.cs590.lab4.product.api.dto.AddProductRequest;
import com.cs590.lab4.product.api.dto.UpdatePriceRequest;
import com.cs590.lab4.product.domain.Product;
import com.cs590.lab4.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@Valid @RequestBody AddProductRequest request) {
        Product product = new Product(
                request.getProductNumber(),
                request.getName(),
                request.getDescription(),
                request.getPrice());
        return productService.addProduct(product);
    }

    @GetMapping("/{productNumber}")
    public Product getProduct(@PathVariable("productNumber") String productNumber) {
        return productService.getProduct(productNumber);
    }

    @PutMapping("/{productNumber}/price")
    public Product updateProductPrice(
            @PathVariable("productNumber") String productNumber,
            @Valid @RequestBody UpdatePriceRequest request) {
        return productService.updateProductPrice(productNumber, request.getPrice());
    }
}
