package com.cs590.lab4.shopping.domain;

import java.math.BigDecimal;

public class CartItem {
    private String productNumber;
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;

    public CartItem() {
    }

    public CartItem(String productNumber, String name, String description, BigDecimal price, int quantity) {
        this.productNumber = productNumber;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
