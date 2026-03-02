package lab.part3.product.client;

import lab.part3.product.dto.ProductChangeEventDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "shopping-service")
public interface ShoppingUpdateClient {

    @PostMapping("/internal/product-change")
    void propagateProductChange(@RequestBody ProductChangeEventDTO event);
}
