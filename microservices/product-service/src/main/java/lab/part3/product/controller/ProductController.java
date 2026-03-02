package lab.part3.product.controller;

import lab.part3.product.dto.ProductDTO;
import lab.part3.product.service.ProductCatalogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private final ProductCatalogService productCatalogService;

    public ProductController(ProductCatalogService productCatalogService) {
        this.productCatalogService = productCatalogService;
    }

    @GetMapping("/products/{productnumber}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable String productnumber) {
        ProductDTO productDTO = productCatalogService.getProduct(productnumber);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<Void> addProduct(@RequestBody ProductDTO productDto) {
        productCatalogService.addProduct(productDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
