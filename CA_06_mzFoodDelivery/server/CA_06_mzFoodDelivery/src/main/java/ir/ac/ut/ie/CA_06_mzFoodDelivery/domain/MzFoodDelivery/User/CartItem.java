package ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.User;



import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.MzRepository;

import java.sql.SQLException;

public class CartItem {
    private String userEmail;
    private String restaurantId;
    private String foodName;
    private int quantity;
    private double unitPrice;

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public CartItem(String userEmail, String restaurantId, String foodName) {
        this.userEmail = userEmail;
        this.restaurantId = restaurantId;
        this.foodName = foodName;
        this.quantity = 1;
    }

    public CartItem(String userEmail, String restaurantId, String foodName, int quantity) {
        this.userEmail = userEmail;
        this.restaurantId = restaurantId;
        this.foodName = foodName;
        this.quantity = quantity;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void increaseQuantity(int quantity) throws SQLException {
        this.quantity += quantity;
        System.out.println("cart item here zhviar");
        System.out.println(this.quantity);
        MzRepository.getInstance().updateCartItem(this);
    }

    public void decreaseQuantity(int quantity) throws SQLException {
        this.quantity -= quantity;
        MzRepository.getInstance().updateCartItem(this);
    }


    public boolean matches(CartItem newCartItem) {
        return this.foodName.equals(newCartItem.foodName) && this.restaurantId.equals(newCartItem.restaurantId);
    }


}
