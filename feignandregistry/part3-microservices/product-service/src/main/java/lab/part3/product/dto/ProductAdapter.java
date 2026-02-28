package lab.part3.product.dto;

import lab.part3.product.domain.Product;

public final class ProductAdapter {
    private ProductAdapter() {
    }

    public static Product toDomain(ProductDTO dto) {
        return new Product(dto.getProductnumber(), dto.getDescription(), dto.getPrice());
    }

    public static ProductDTO toDto(Product product) {
        return new ProductDTO(product.getProductnumber(), product.getDescription(), product.getPrice());
    }
}
