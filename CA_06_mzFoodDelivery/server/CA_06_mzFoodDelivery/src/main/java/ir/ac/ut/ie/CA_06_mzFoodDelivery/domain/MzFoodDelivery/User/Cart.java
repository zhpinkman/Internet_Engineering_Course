package ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.User;

import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Food;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.PartyFood;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Restaurant;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.MzRepository;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.utils.CustomPair;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    public static final String userEmail = "ekhamespanah@yahoo.com";

    private CartItem findCartItem(CartItem newCartItem) throws SQLException {
        List<CartItem> cartItems = MzRepository.getInstance().getUserCart(userEmail);
        for (CartItem cartItem: cartItems) {
            if (cartItem.matches(newCartItem)) {
                return cartItem;
            }
        }
        return null;
    }

    public List<CartItem> getCartItems() throws SQLException {
        return MzRepository.getInstance().getUserCart(userEmail);
    }

    private boolean doesRestaurantMatch(List<CartItem> userCart, CartItem newCartItem){
        if (userCart.size() == 0) return true;
        return userCart.get(0).getRestaurantId().equals(newCartItem.getRestaurantId());
    }

    public Restaurant getRestaurant() throws SQLException {
        List<CartItem> userCart = MzRepository.getInstance().getUserCart(userEmail);
        if (userCart.size() > 0) {
            String restaurantId = userCart.get(0).getRestaurantId();
            return MzRepository.getInstance().findRestaurantById(restaurantId);
        }
        return null;
    }

    public void addToCart(CartItem newCartItem) throws Exception {
        List<CartItem> userCart = MzRepository.getInstance().getUserCart(userEmail);
        if (!doesRestaurantMatch(userCart, newCartItem))
            throw new Exception("Error: you have some food from another restaurant, then you can not add foods from another restaurant to your cart");

        CartItem cartItem = findCartItem(newCartItem);
        if (cartItem != null)
            cartItem.increaseQuantity(newCartItem.getQuantity());
        else
            MzRepository.getInstance().insertCartItem(newCartItem);
    }

    public void emptyCart(){
        try {
            MzRepository.getInstance().emptyUserCart(userEmail);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getSize() throws SQLException {
        int cartSize = 0;
        List<CartItem> cartItems = MzRepository.getInstance().getUserCart(userEmail);
        for (CartItem cartItem: cartItems) {
            cartSize += cartItem.getQuantity();
        }
        return cartSize;
    }

    public double getTotalPrice() throws SQLException {
        double totalPrice = 0;
        List<CartItem> cartItems = MzRepository.getInstance().getUserCart(userEmail);
        for (CartItem cartItem: cartItems) {
            Food food = MzRepository.getInstance().getFood(cartItem.getRestaurantId(), cartItem.getFoodName());
            totalPrice += cartItem.getQuantity() * food.getPrice();
        }
        return totalPrice;
    }


    public synchronized void removeCartItem(PartyFood partyFood) throws SQLException {
        MzRepository.getInstance().removeCartItem(new CartItem(userEmail, partyFood.getRestaurantId(), partyFood.getName()));
    }

    public void delete(String restaurantId, String foodName) throws Exception {
        CartItem tempCartItem = new CartItem(userEmail, restaurantId, foodName);
        CartItem cartItem = MzRepository.getInstance().findCartItem(tempCartItem);
        if (cartItem.getQuantity() > 1) {
            cartItem.decreaseQuantity(1);
        }
        else if (cartItem.getQuantity() == 1){
            MzRepository.getInstance().removeCartItem(cartItem);
        }
    }
}
