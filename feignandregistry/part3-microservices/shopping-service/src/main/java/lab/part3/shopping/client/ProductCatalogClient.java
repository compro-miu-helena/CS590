package lab.part3.shopping.client;

import lab.part3.shopping.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductCatalogClient {

    @GetMapping("/products/{productnumber}")
    ProductDTO getProduct(@PathVariable String productnumber);
}
