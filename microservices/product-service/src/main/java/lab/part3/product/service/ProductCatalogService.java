package lab.part3.product.service;

import java.util.Optional;

import lab.part3.product.client.ShoppingUpdateClient;
import lab.part3.product.domain.Product;
import lab.part3.product.dto.ProductAdapter;
import lab.part3.product.dto.ProductChangeEventDTO;
import lab.part3.product.dto.ProductDTO;
import lab.part3.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductCatalogService {
    private final ProductRepository productRepository;
    private final ShoppingUpdateClient shoppingUpdateClient;

    public ProductCatalogService(ProductRepository productRepository, ShoppingUpdateClient shoppingUpdateClient) {
        this.productRepository = productRepository;
        this.shoppingUpdateClient = shoppingUpdateClient;
    }

    public void addProduct(ProductDTO productDto) {
        Product product = ProductAdapter.toDomain(productDto);
        boolean exists = productRepository.findById(product.getProductnumber()).isPresent();
        productRepository.save(product);

        if (exists) {
            shoppingUpdateClient.propagateProductChange(new ProductChangeEventDTO(productDto));
        }
    }

    public ProductDTO getProduct(String productnumber) {
        Optional<Product> result = productRepository.findById(productnumber);
        return result.map(ProductAdapter::toDto).orElse(null);
    }
}
