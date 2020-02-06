package McFayyaz;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> cartItems = new ArrayList<CartItem>();

    public void addToCart(CartItem newCartItem) throws Exception {
        for (CartItem cartItem: cartItems) {
            if (cartItem.getFood().getName().equals(newCartItem.getFood().getName())) {
                throw new Exception("Error: item already exists in cart");
            }
        }
        cartItems.add(newCartItem);
    }

    public Restaurant getRestaurant() {
        if (cartItems.size() > 0) {
            return cartItems.get(0).getRestaurant();
        }
        return null;
    }
}
