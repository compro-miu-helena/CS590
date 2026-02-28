package lab.part3.shopping.dto;

import lab.part3.shopping.domain.Product;

public final class ProductAdapter {
    private ProductAdapter() {
    }

    public static Product toDomain(ProductDTO productDTO) {
        return new Product(productDTO.getProductnumber(), productDTO.getDescription(), productDTO.getPrice());
    }

    public static ProductDTO toDto(Product product) {
        return new ProductDTO(product.getProductnumber(), product.getDescription(), product.getPrice());
    }
}
