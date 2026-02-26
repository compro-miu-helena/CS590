package com.cs590.lab4.shopping.service;

import com.cs590.lab4.shopping.domain.CartItem;
import com.cs590.lab4.shopping.domain.ShoppingCart;
import com.cs590.lab4.shopping.integration.ProductClient;
import com.cs590.lab4.shopping.integration.ProductResponse;
import com.cs590.lab4.shopping.repository.ShoppingCartRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ShoppingService {
    private static final String DEFAULT_CART_ID = "default-cart";

    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductClient productClient;

    public ShoppingService(ShoppingCartRepository shoppingCartRepository, ProductClient productClient) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productClient = productClient;
    }

    public ShoppingCart addToShoppingCart(String productNumber, int quantity) {
        ProductResponse product = productClient.getProductByNumber(productNumber);
        ShoppingCart cart = getOrCreateCart();

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(i -> i.getProductNumber().equals(productNumber))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            refreshItemDetails(item, product);
        } else {
            cart.getItems().add(new CartItem(
                    product.getProductNumber(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    quantity));
        }

        return shoppingCartRepository.save(cart);
    }

    public ShoppingCart getShoppingCart() {
        ShoppingCart cart = getOrCreateCart();
        for (CartItem item : cart.getItems()) {
            ProductResponse product = productClient.getProductByNumber(item.getProductNumber());
            refreshItemDetails(item, product);
        }
        return shoppingCartRepository.save(cart);
    }

    private ShoppingCart getOrCreateCart() {
        return shoppingCartRepository.findById(DEFAULT_CART_ID)
                .orElseGet(() -> shoppingCartRepository.save(new ShoppingCart(DEFAULT_CART_ID)));
    }

    private void refreshItemDetails(CartItem item, ProductResponse product) {
        item.setName(product.getName());
        item.setDescription(product.getDescription());
        item.setPrice(product.getPrice());
    }
}
