package lab.part3.shopping.service;

import java.util.List;
import java.util.Optional;

import lab.part3.shopping.client.ProductCatalogClient;
import lab.part3.shopping.domain.Product;
import lab.part3.shopping.domain.ShoppingCart;
import lab.part3.shopping.dto.ProductDTO;
import lab.part3.shopping.dto.ShoppingCartAdapter;
import lab.part3.shopping.dto.ShoppingCartDTO;
import lab.part3.shopping.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

@Service
public class ShoppingService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductCatalogClient productCatalogClient;

    public ShoppingService(ShoppingCartRepository shoppingCartRepository, ProductCatalogClient productCatalogClient) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productCatalogClient = productCatalogClient;
    }

    public void addToCart(String cartId, String productnumber, int quantity) {
        ProductDTO productDto = productCatalogClient.getProduct(productnumber);
        if (productDto == null) {
            return;
        }

        Product product = new Product(productDto.getProductnumber(), productDto.getDescription(), productDto.getPrice());
        Optional<ShoppingCart> cartOptional = shoppingCartRepository.findById(cartId);
        if (cartOptional.isPresent()) {
            ShoppingCart cart = cartOptional.get();
            cart.addToCart(product, quantity);
            shoppingCartRepository.save(cart);
        } else {
            ShoppingCart cart = new ShoppingCart();
            cart.setCartid(cartId);
            cart.addToCart(product, quantity);
            shoppingCartRepository.save(cart);
        }
    }

    public ShoppingCartDTO getCart(String cartId) {
        Optional<ShoppingCart> cart = shoppingCartRepository.findById(cartId);
        return cart.map(ShoppingCartAdapter::toDto).orElse(null);
    }

    public void updateProductInAllCarts(Product updatedProduct) {
        List<ShoppingCart> carts = shoppingCartRepository.findAll();
        for (ShoppingCart cart : carts) {
            cart.updateProduct(updatedProduct);
        }
        shoppingCartRepository.saveAll(carts);
    }
}
