package services.User;

import services.Restaurant.Food;
import services.Restaurant.Restaurant;

public class CartItem {
    private Restaurant restaurant;
    private Food food;
    private int quantity = 1;

    public int getQuantity() {
        return quantity;
    }

    public CartItem(Restaurant restaurant, Food food) {
        this.restaurant = restaurant;
        this.food = food;
    }

    public Food getFood() {
        return food;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void increaseQuantity() {
        quantity += 1;
    }
}
