package McFayyaz.User;

import McFayyaz.Restaurant.Food;
import McFayyaz.Restaurant.Restaurant;

public class CartItem {
    private Restaurant restaurant;
    private Food food;
    private int quantity = 1;

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
