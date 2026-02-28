package lab.part3.client;

import lab.part3.client.dto.AddToCartDTO;
import lab.part3.client.dto.ProductDTO;
import lab.part3.client.dto.ShoppingCartDTO;
import lab.part3.client.feign.ProductServiceClient;
import lab.part3.client.feign.ShoppingServiceClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WebShopClientApplication implements CommandLineRunner {
    private final ProductServiceClient productServiceClient;
    private final ShoppingServiceClient shoppingServiceClient;

    public WebShopClientApplication(ProductServiceClient productServiceClient, ShoppingServiceClient shoppingServiceClient) {
        this.productServiceClient = productServiceClient;
        this.shoppingServiceClient = shoppingServiceClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(WebShopClientApplication.class, args);
    }

    @Override
    public void run(String... args) {
        productServiceClient.addProduct(new ProductDTO("A33", "TV", 450.0));
        productServiceClient.addProduct(new ProductDTO("A34", "MP3 Player", 75.0));

        shoppingServiceClient.addToCart("1", new AddToCartDTO("A33", 3));
        shoppingServiceClient.addToCart("1", new AddToCartDTO("A34", 2));

        ShoppingCartDTO cart = shoppingServiceClient.getCart("1");
        System.out.println("\n-----Shoppingcart-------");
        printCart(cart);

        productServiceClient.addProduct(new ProductDTO("A33", "TV", 550.0));

        cart = shoppingServiceClient.getCart("1");
        System.out.println("\n-----Shoppingcart after price change-------");
        printCart(cart);
    }

    private void printCart(ShoppingCartDTO cart) {
        if (cart == null) {
            System.out.println("Cart not found");
            return;
        }
        System.out.println("Content of the shoppingcart:");
        cart.getCartlineList().forEach(cline -> System.out.println(
            cline.getQuantity() + " " +
            cline.getProduct().getProductnumber() + " " +
            cline.getProduct().getDescription() + " " +
            cline.getProduct().getPrice()));
        System.out.println("Total price = " + cart.getTotalPrice());
    }
}
