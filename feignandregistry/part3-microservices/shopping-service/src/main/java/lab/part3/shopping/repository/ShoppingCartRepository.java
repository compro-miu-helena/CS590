package lab.part3.shopping.repository;

import lab.part3.shopping.domain.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {
}
