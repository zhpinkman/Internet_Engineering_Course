package McFayyaz;

import java.util.ArrayList;
import java.util.List;

public class McZmo {
    List<Restaurant> restaurants = new ArrayList<Restaurant>();
    User user = new User();


    public void addRestaurant(Restaurant restaurant) throws Exception{
        if (restaurants.contains(restaurant))
            throw new Exception("Error: Duplicate restaurant");
        restaurants.add(restaurant);
    }

    public void addFood(String restaurantName, Food food) throws Exception {
        for (Restaurant restaurant: restaurants) {
            if (restaurant.getName().equals(restaurantName)) {
                restaurant.addFood(food);
                return;
            }
        }
        throw new Exception("Error: restaurant does not exists");
    }

    public void printRestaurants() {
        for (Restaurant restaurant: restaurants) {
            System.out.println(restaurant.getName());
        }
    }

    public Restaurant getRestaurant(String restaurantName) throws Exception {
        for (Restaurant restaurant: restaurants) {
            if (restaurant.getName().equals(restaurantName)) {
                return restaurant;
            }
        }
        throw new Exception("Error: restaurant does not exists");
    }

    public Food getFood(String restaurantName, String foodName) throws Exception {
        for (Restaurant restaurant: restaurants) {
            if (restaurant.getName().equals(restaurantName)) {
                return restaurant.getFood(foodName);
            }
        }
        throw new Exception("Error: restaurant does not exists");
    }

    public void addToCart(String restaurantName, String foodName) throws Exception{
        for (Restaurant restaurant: restaurants) {
            if (restaurant.getName().equals(restaurantName)) {
                Restaurant activeRestaurant = user.getActiveRestaurant();
                if (activeRestaurant != null && !activeRestaurant.getName().equals(restaurantName))
                    throw new Exception("Error: you have some food from another restaurant, then you can not add foods from another restaurant to your cart");
                Food food = restaurant.getFood(foodName);
                CartItem cartItem = new CartItem(restaurant, food);
                user.addToCart(cartItem);
                return;
            }
        }
        throw new Exception("Error: restaurant does not exists");
    }
}
