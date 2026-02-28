package shop.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import shop.shopping.service.dto.ProductChangeEventDTO;
import shop.shopping.domain.Product;
import shop.shopping.service.dto.ProductDTO;

@Component
public class ProductChangeListener {
    @Autowired
    ProductChangeHandler productChangeHandler;

    @EventListener
    public void onEvent(ProductChangeEventDTO event) {
        ProductDTO productDto = event.getProduct();
        //create a shopping product from a products product
        Product product = new Product(productDto.getProductnumber(),productDto.getDescription(),productDto.getPrice());
        productChangeHandler.handleProductChange(product);
    }
}
