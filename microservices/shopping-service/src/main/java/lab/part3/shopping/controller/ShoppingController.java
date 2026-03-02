package lab.part3.shopping.controller;

import lab.part3.shopping.domain.Product;
import lab.part3.shopping.dto.AddToCartDTO;
import lab.part3.shopping.dto.ProductChangeEventDTO;
import lab.part3.shopping.dto.ProductDTO;
import lab.part3.shopping.dto.ShoppingCartDTO;
import lab.part3.shopping.service.ShoppingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingController {
    private final ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @PostMapping("/cart/{cartId}")
    public ResponseEntity<Void> addToCart(@PathVariable String cartId, @RequestBody AddToCartDTO addToCartDTO) {
        shoppingService.addToCart(cartId, addToCartDTO.getProductnumber(), addToCartDTO.getQuantity());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/cart/{cartId}")
    public ResponseEntity<ShoppingCartDTO> getCart(@PathVariable String cartId) {
        ShoppingCartDTO cart = shoppingService.getCart(cartId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/internal/product-change")
    public ResponseEntity<Void> onProductChange(@RequestBody ProductChangeEventDTO event) {
        ProductDTO dto = event.getProduct();
        Product updatedProduct = new Product(dto.getProductnumber(), dto.getDescription(), dto.getPrice());
        shoppingService.updateProductInAllCarts(updatedProduct);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
