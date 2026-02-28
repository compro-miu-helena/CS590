package lab.part3.shopping.domain;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ShoppingCart {
    @Id
    private String cartid;
    private ArrayList<CartLine> cartlineList = new ArrayList<>();

    public void addToCart(Product product, int quantity) {
        for (CartLine cline : cartlineList) {
            if (cline.getProduct().getProductnumber().equals(product.getProductnumber())) {
                cline.setQuantity(cline.getQuantity() + quantity);
                return;
            }
        }
        CartLine cline = new CartLine();
        cline.setProduct(product);
        cline.setQuantity(quantity);
        cartlineList.add(cline);
    }

    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (CartLine cline : cartlineList) {
            totalPrice = totalPrice + (cline.getProduct().getPrice() * cline.getQuantity());
        }
        return totalPrice;
    }

    public void removeFromCart(Product product) {
        Iterator<CartLine> iter = cartlineList.iterator();
        while (iter.hasNext()) {
            CartLine cline = iter.next();
            if (cline.getProduct().getProductnumber().equals(product.getProductnumber())) {
                if (cline.getQuantity() > 1) {
                    cline.setQuantity(cline.getQuantity() - 1);
                } else {
                    iter.remove();
                }
            }
        }
    }

    public void updateProduct(Product product) {
        for (CartLine cline : cartlineList) {
            if (cline.getProduct().getProductnumber().equals(product.getProductnumber())) {
                cline.setProduct(product);
            }
        }
    }

    public String getCartid() {
        return cartid;
    }

    public void setCartid(String cartid) {
        this.cartid = cartid;
    }

    public ArrayList<CartLine> getCartlineList() {
        return cartlineList;
    }

    public void setCartlineList(ArrayList<CartLine> cartlineList) {
        this.cartlineList = cartlineList;
    }
}
