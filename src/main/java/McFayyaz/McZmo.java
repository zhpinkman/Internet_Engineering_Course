package McFayyaz;

import McFayyaz.Restaurant.Food;
import McFayyaz.Restaurant.Location;
import McFayyaz.Restaurant.Restaurant;
import McFayyaz.User.CartItem;
import McFayyaz.User.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class McZmo {
    List<Restaurant> restaurants = new ArrayList<Restaurant>();
    User user = new User();

    public boolean doesRestaurantExist(Restaurant restaurant){
        for(Restaurant restaurantItem : restaurants)
            if(restaurantItem.isCopy(restaurant))
                return true;
        return false;
    }

    public void addRestaurant(Restaurant restaurant) throws Exception{
        if (doesRestaurantExist(restaurant))
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
        throw new Exception("Error: " + restaurantName + " restaurant does not exists");
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

    public void printRecommendedRestaurants() {
        Collections.sort(restaurants, new SortByAveragePopularityDistance(user.getLocation()));
        List<Restaurant> firstThreeRestaurants = restaurants.subList(0, 3);
        for (Restaurant restaurant: firstThreeRestaurants) {
            System.out.println(restaurant.getName());
        }
    }

}

class SortByAveragePopularityDistance implements Comparator<Restaurant> {

    private Location location;

    public SortByAveragePopularityDistance(Location location) {
        this.location = location;
    }


    @Override
    public int compare(Restaurant r1, Restaurant r2) {
        double r1FoodsAverage = r1.getFoodsPopularityAverage();
        double r2FoodsAverage = r2.getFoodsPopularityAverage();
        double r1DistanceFromUser = r1.getDistanceFromLocation(location);
        double r2DistanceFromUser = r2.getDistanceFromLocation(location);
        int r1Rank = (int) (r1FoodsAverage / r1DistanceFromUser);
        int r2Rank = (int) (r2FoodsAverage / r2DistanceFromUser);
        return r1Rank - r2Rank;
    }
}
