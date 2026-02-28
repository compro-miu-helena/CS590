package lab.part3.shopping.dto;

import lab.part3.shopping.domain.CartLine;
import lab.part3.shopping.domain.ShoppingCart;

public final class ShoppingCartAdapter {
    private ShoppingCartAdapter() {
    }

    public static ShoppingCartDTO toDto(ShoppingCart shoppingCart) {
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        shoppingCartDTO.setCartid(shoppingCart.getCartid());
        shoppingCartDTO.setTotalPrice(shoppingCart.getTotalPrice());
        for (CartLine cartLine : shoppingCart.getCartlineList()) {
            CartLineDTO cartLineDTO = new CartLineDTO();
            cartLineDTO.setQuantity(cartLine.getQuantity());
            cartLineDTO.setProduct(ProductAdapter.toDto(cartLine.getProduct()));
            shoppingCartDTO.addCartLine(cartLineDTO);
        }
        return shoppingCartDTO;
    }
}
