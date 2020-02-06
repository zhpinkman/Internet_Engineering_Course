package McFayyaz.User;

import McFayyaz.Restaurant.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> cartItems = new ArrayList<>();

    private CartItem findCartItem(CartItem newCartItem){
        for (CartItem cartItem: cartItems) {
            if (cartItem.getFood().getName().equals(newCartItem.getFood().getName())) {
                return cartItem;
            }
        }
        return null;
    }

    private boolean doesRestaurantMatch(CartItem newCartItem){
        Restaurant activeRestaurant = this.getRestaurant();
        return activeRestaurant != null && !activeRestaurant.getName().equals(newCartItem.getRestaurant().getName());
    }

    public Restaurant getRestaurant() {
        if (cartItems.size() > 0) {
            return cartItems.get(0).getRestaurant();
        }
        return null;
    }

    public void addToCart(CartItem newCartItem) throws Exception {
        if (doesRestaurantMatch(newCartItem))
            throw new Exception("Error: you have some food from another restaurant, then you can not add foods from another restaurant to your cart");

        CartItem cartItem = findCartItem(newCartItem);
        if (cartItem != null)
            cartItem.increaseQuantity();
        else
            cartItems.add(newCartItem);
    }

    public void emptyCart(){
        cartItems.clear();
    }
}
