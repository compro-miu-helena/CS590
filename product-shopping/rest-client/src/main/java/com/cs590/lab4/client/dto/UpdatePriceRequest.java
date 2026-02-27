package com.cs590.lab4.client.dto;

import java.math.BigDecimal;

public class UpdatePriceRequest {
    private BigDecimal price;

    public UpdatePriceRequest() {
    }

    public UpdatePriceRequest(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
