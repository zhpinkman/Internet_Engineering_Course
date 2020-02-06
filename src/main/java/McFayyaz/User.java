package McFayyaz;

public class User {
    private Cart userCart = new Cart();
    public void addToCart(CartItem cartItem) throws Exception {
        userCart.addToCart(cartItem);
    }

    public Cart getUserCart() {
        return userCart;
    }

    public Restaurant getActiveRestaurant() {
        return userCart.getRestaurant();
    }
}
