package lab.part3.client.dto;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDTO {
    private String cartid;
    private double totalPrice;
    private List<CartLineDTO> cartlineList = new ArrayList<>();

    public String getCartid() {
        return cartid;
    }

    public void setCartid(String cartid) {
        this.cartid = cartid;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartLineDTO> getCartlineList() {
        return cartlineList;
    }

    public void setCartlineList(List<CartLineDTO> cartlineList) {
        this.cartlineList = cartlineList;
    }
}
