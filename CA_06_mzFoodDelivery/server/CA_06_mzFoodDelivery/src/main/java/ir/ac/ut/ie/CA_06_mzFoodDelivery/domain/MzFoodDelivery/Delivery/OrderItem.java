package ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Delivery;

import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.User.CartItem;

public class OrderItem {
    private String restaurantId;
    private String foodName;
    private String userEmail;
    private int id;
    private int quantity;


    public OrderItem(String userEmail, int id, String restaurantId, String foodName, int quantity) {
        this.restaurantId = restaurantId;
        this.foodName = foodName;
        this.userEmail = userEmail;
        this.id = id;
        this.quantity = quantity;
    }

    public OrderItem(String userEmail, int id, CartItem cartItem) {
        this.userEmail = userEmail;
        this.id = id;
        this.restaurantId = cartItem.getRestaurantId();
        this.foodName = cartItem.getFoodName();
        this.quantity = cartItem.getQuantity();
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
