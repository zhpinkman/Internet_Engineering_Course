package McFayyaz;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class McZmo {
    List<Restaurant> restaurants;

    public McZmo() {
        restaurants = new ArrayList<Restaurant>();
    }

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
}
