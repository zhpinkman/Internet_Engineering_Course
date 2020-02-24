package MzFoodDelivery.Restaurant;

import MzFoodDelivery.Exceptions.RestaurantIsNotNearUserException;
import MzFoodDelivery.Exceptions.RestaurantNotFoundException;
import MzFoodDelivery.Delivery.Order;
import MzFoodDelivery.User.Cart;
import MzFoodDelivery.User.CartItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantManager {
    private List<Restaurant> restaurants = new ArrayList<>();

    private boolean doesRestaurantExist(Restaurant restaurant) {
        for (Restaurant restaurantItem : restaurants)
            if (restaurantItem.isCopy(restaurant))
                return true;
        return false;
    }

    private Restaurant findRestaurantById(String id){
        for (Restaurant restaurantItem : restaurants)
            if (restaurantItem.getId().equals(id))
                return restaurantItem;
        return null;
    }

    private Restaurant findRestaurantByName(String name){
        for (Restaurant restaurantItem : restaurants)
            if (restaurantItem.getName().equals(name))
                return restaurantItem;
        return null;
    }

    public void addRestaurant(Restaurant restaurant) throws Exception {
        if (doesRestaurantExist(restaurant))
            throw new Exception("Error: Duplicate restaurant");
        restaurants.add(restaurant);
    }

    public void addFood(String restaurantName, Food food) throws Exception {
        Restaurant restaurant = findRestaurantByName(restaurantName);
        if(restaurant == null)
            throw new Exception("Error: " + restaurantName + " restaurant does not exists");
        restaurant.addFood(food);
    }

    public void printRestaurants() {
        if (restaurants.size() == 0) {
            System.out.println("No added Restaurants yet");
        }
        for (Restaurant restaurant : restaurants) {
            System.out.println(restaurant.getName());
        }
    }

    public Restaurant getRestaurant(String restaurantName) throws Exception {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().equals(restaurantName)) {
                return restaurant;
            }
        }
        throw new Exception("Error: restaurant does not exists");
    }

    public Food getFood(String restaurantName, String foodName) throws Exception {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().equals(restaurantName)) {
                return restaurant.getFood(foodName);
            }
        }
        throw new Exception("Error: restaurant does not exists");
    }

    public List<Restaurant> getRecommendedRestaurants(Location userLocation, int recommendCount) {
//        for (Restaurant restaurant: restaurants) {
//            System.out.println(restaurant.getName());
//        }
        restaurants.sort(new SortByAveragePopularityDistance(userLocation));
        Collections.reverse(restaurants);

//        for (Restaurant restaurant: restaurants) {
//            System.out.println(restaurant.getName());
//        }

        return restaurants.subList(0, Math.min(recommendCount, restaurants.size()));
    }

    public List<Restaurant> getNearRestaurants(Location userLocation) {
        restaurants.sort(new SortByDistance(userLocation));
        return restaurants.stream().filter(it -> it.isNearUser(userLocation)).collect(Collectors.toList());
    }

    public Restaurant getNearRestaurantById(String restaurantId, Location userLocation) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        if(restaurant == null)
            throw new RestaurantNotFoundException("Error: restaurant does not exists");
        else if(!restaurant.isNearUser(userLocation))
            throw new RestaurantIsNotNearUserException("Error: This restaurant is not near you!");
        else
            return restaurant;
    }

    public String getRestaurantNameById(String restaurantId) throws Exception {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId().equals(restaurantId)) {
                return restaurant.getName();
            }
        }
        throw new Exception("Error: restaurant does not exists");
    }

    public void decreaseFoodAmounts(Order order) throws Exception {
        Cart cart = order.getCart();
        for (CartItem cartItem: cart.getCartItems()) {
            cartItem.getFood().decreaseFoodAmount();
        }
    }
}



