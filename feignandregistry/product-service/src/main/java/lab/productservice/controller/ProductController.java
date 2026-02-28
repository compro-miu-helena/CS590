package lab.productservice.controller;

import lab.productservice.client.StockClient;
import lab.productservice.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final StockClient stockClient;

    public ProductController(StockClient stockClient) {
        this.stockClient = stockClient;
    }

    @GetMapping("/{productNumber}")
    public Product getProduct(@PathVariable int productNumber) {
        int stock = stockClient.getStock(productNumber);
        return new Product(productNumber, resolveName(productNumber), stock);
    }

    private String resolveName(int productNumber) {
        return switch (productNumber) {
            case 1 -> "Laptop";
            case 2 -> "Keyboard";
            case 3 -> "Mouse";
            default -> "Generic product";
        };
    }
}
