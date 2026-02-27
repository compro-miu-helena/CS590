package com.cs590.lab4.client.dto;

import java.math.BigDecimal;

public class ProductDto {
    private String id;
    private String productNumber;
    private String name;
    private String description;
    private BigDecimal price;

    public ProductDto() {
    }

    public ProductDto(String productNumber, String name, String description, BigDecimal price) {
        this.productNumber = productNumber;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "productNumber='" + productNumber + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
