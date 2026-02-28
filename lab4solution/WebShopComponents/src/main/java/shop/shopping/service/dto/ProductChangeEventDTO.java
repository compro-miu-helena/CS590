package shop.shopping.service.dto;

import shop.shopping.service.dto.ProductDTO;

public class ProductChangeEventDTO {
    private ProductDTO product;

    public ProductChangeEventDTO(ProductDTO product) {
        this.product = product;
    }

    public ProductDTO getProduct() {
        return product;
    }
}
