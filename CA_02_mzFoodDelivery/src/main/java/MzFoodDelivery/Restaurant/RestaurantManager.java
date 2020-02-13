package MzFoodDelivery.Restaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public void addRestaurant(Restaurant restaurant) throws Exception {
        if (doesRestaurantExist(restaurant))
            throw new Exception("Error: Duplicate restaurant");
        restaurants.add(restaurant);
    }

    public void addFood(String restaurantName, Food food) throws Exception {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().equals(restaurantName)) {
                restaurant.addFood(food);
                return;
            }
        }
        throw new Exception("Error: " + restaurantName + " restaurant does not exists");
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
        return restaurants.stream().filter(it -> it.getDistanceFromLocation(userLocation) < 170).collect(Collectors.toList());
    }
}


class SortByAveragePopularityDistance implements Comparator<Restaurant> {
    private Location location;

    SortByAveragePopularityDistance(Location location) {
        this.location = location;
    }

    @Override
    public int compare(Restaurant r1, Restaurant r2) {
        double r1FoodsPopularityAverage = r1.getFoodsPopularityAverage();
        double r2FoodsPopularityAverage = r2.getFoodsPopularityAverage();
        double r1DistanceFromUser = r1.getDistanceFromLocation(location);
        double r2DistanceFromUser = r2.getDistanceFromLocation(location);
        int r1Rank = (int) (r1FoodsPopularityAverage / r1DistanceFromUser);
        int r2Rank = (int) (r2FoodsPopularityAverage / r2DistanceFromUser);
        return r1Rank - r2Rank;
    }
}

class SortByDistance implements Comparator<Restaurant> {
    private Location location;

    SortByDistance(Location location) {
        this.location = location;
    }

    @Override
    public int compare(Restaurant r1, Restaurant r2) {
        return (int) r1.getDistanceFromLocation(location) - (int) r2.getDistanceFromLocation(location);
    }
}