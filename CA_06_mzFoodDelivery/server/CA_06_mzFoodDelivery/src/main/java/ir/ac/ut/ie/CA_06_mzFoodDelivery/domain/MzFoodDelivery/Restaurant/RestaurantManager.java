package ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant;



import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Delivery.Order;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Exceptions.RestaurantIsNotNearUserException;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Exceptions.RestaurantNotFoundException;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.User.Cart;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.User.CartItem;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.MzRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantManager {
    private List<Restaurant> restaurants = new ArrayList<>();
    private final double MAX_NEAR_DISTANCE = 170;

    private boolean doesRestaurantExist(Restaurant restaurant) {
        for (Restaurant restaurantItem : restaurants)
            if (restaurantItem.isCopy(restaurant))
                return true;
        return false;
    }

    public Restaurant findRestaurantById(String id){
        try {
            return MzRepository.getInstance().findRestaurantById(id, true);
        }catch (SQLException e){
            return null;
        }
//        for (Restaurant restaurantItem : restaurants)
//            if (restaurantItem.getId().equals(id))
//                return restaurantItem;
//        return null;
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
//            System.out.println(restaurant.getName());
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

//    public List<Restaurant> getRecommendedRestaurants(Location userLocation, int recommendCount) {
////        for (Restaurant restaurant: restaurants) {
////            System.out.println(restaurant.getName());
////        }
//        restaurants.sort(new SortByAveragePopularityDistance(userLocation));
//        Collections.reverse(restaurants);
//
////        for (Restaurant restaurant: restaurants) {
////            System.out.println(restaurant.getName());
////        }
//
//        return restaurants.subList(0, Math.min(recommendCount, restaurants.size()));
//    }

    public synchronized List<Restaurant> getNearRestaurants(Location userLocation, int limit, int offset) throws SQLException{
        return MzRepository.getInstance().findNearRestaurants(userLocation, MAX_NEAR_DISTANCE, limit, offset);
//        restaurants.sort(new SortByDistance(userLocation));
//        return restaurants.stream().filter(it -> it.isNearUser(userLocation)).collect(Collectors.toList());
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

    public void decreaseFoodAmounts(List<CartItem> cartItems) throws SQLException {
        for (CartItem cartItem: cartItems) {
            Food food = MzRepository.getInstance().getFood(cartItem.getRestaurantId(), cartItem.getFoodName());
            food.decreaseFoodAmount(cartItem.getQuantity());
        }
    }
}

