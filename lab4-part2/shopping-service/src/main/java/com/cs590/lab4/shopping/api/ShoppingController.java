package com.cs590.lab4.shopping.api;

import com.cs590.lab4.shopping.api.dto.AddToShoppingCartRequest;
import com.cs590.lab4.shopping.domain.ShoppingCart;
import com.cs590.lab4.shopping.service.ShoppingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoppingcart")
public class ShoppingController {
    private final ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingCart addToShoppingCart(@Valid @RequestBody AddToShoppingCartRequest request) {
        return shoppingService.addToShoppingCart(request.getProductNumber(), request.getQuantity());
    }

    @GetMapping
    public ShoppingCart getShoppingCart() {
        return shoppingService.getShoppingCart();
    }
}
