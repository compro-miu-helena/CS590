package lab.part3.client.feign;

import lab.part3.client.dto.AddToCartDTO;
import lab.part3.client.dto.ShoppingCartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "shopping-service")
public interface ShoppingServiceClient {

    @PostMapping("/cart/{cartId}")
    void addToCart(@PathVariable String cartId, @RequestBody AddToCartDTO addToCartDTO);

    @GetMapping("/cart/{cartId}")
    ShoppingCartDTO getCart(@PathVariable String cartId);
}
