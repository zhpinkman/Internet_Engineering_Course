package McFayyaz.User;

import McFayyaz.Restaurant.Location;
import McFayyaz.Restaurant.Restaurant;

public class User {
    private Cart userCart = new Cart();
    private Location location = new Location(0, 0);
    public void addToCart(CartItem cartItem) throws Exception {
        userCart.addToCart(cartItem);
    }

    public Cart getUserCart() {
        return userCart;
    }

    public Restaurant getActiveRestaurant() {
        return userCart.getRestaurant();
    }

    public Location getLocation() {
        return location;
    }

    public void finalizeOrder() {
        userCart.emptyCart();
    }

    public int getUserCartSize() {
        return userCart.getSize();
    }
}
