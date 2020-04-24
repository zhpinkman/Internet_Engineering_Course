package ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Delivery;

import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Food;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.User.CartItem;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.MzRepository;

import java.sql.SQLException;

public class OrderItem {
    private String restaurantId;
    private String foodName;
    private String userEmail;
    private int id;
    private int quantity;
    private double foodPrice;


    public OrderItem(String userEmail, int id, String restaurantId, String foodName, int quantity, double foodPrice) {
        this.restaurantId = restaurantId;
        this.foodName = foodName;
        this.userEmail = userEmail;
        this.id = id;
        this.quantity = quantity;
        this.foodPrice = foodPrice;
    }

    public OrderItem(String userEmail, int id, CartItem cartItem, double foodPrice) {
        this.userEmail = userEmail;
        this.id = id;
        this.restaurantId = cartItem.getRestaurantId();
        this.foodName = cartItem.getFoodName();
        this.quantity = cartItem.getQuantity();
        this.foodPrice = foodPrice;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }
}
