package McFayyaz;

import McFayyaz.Restaurant.Food;
import McFayyaz.Restaurant.Restaurant;
import McFayyaz.Restaurant.RestaurantManager;
import McFayyaz.User.Cart;
import McFayyaz.User.CartItem;
import McFayyaz.User.UserManager;

import java.util.List;

public class McZmo {
    private RestaurantManager restaurantManager = new RestaurantManager();
    private UserManager userManager = new UserManager();

    //    RESTAURANT MANAGER
    public void addRestaurant(Restaurant restaurant) throws Exception {
        restaurantManager.addRestaurant(restaurant);
    }

    public void addFood(String restaurantName, Food food) throws Exception {
        restaurantManager.addFood(restaurantName, food);
    }

    public void printRestaurants() {
        restaurantManager.printRestaurants();
    }

    public Restaurant getRestaurant(String restaurantName) throws Exception {
        return restaurantManager.getRestaurant(restaurantName);
    }

    public Food getFood(String restaurantName, String foodName) throws Exception {
        return restaurantManager.getFood(restaurantName, foodName);
    }

    //    USER MANAGER
    public void addToCart(String restaurantName, String foodName) throws Exception {
        Restaurant restaurant = getRestaurant(restaurantName);
        Food food = restaurant.getFood(foodName);
        CartItem cartItem = new CartItem(restaurant, food);
        userManager.addToCart(cartItem);
    }

    public Cart getCart() {
        return userManager.getCart();
    }

    public String getBriefCartJson() {
        return userManager.getBriefCartJson();
    }

    public void finalizeOrder() {
        userManager.finalizeOrder();
    }

    public int getUserCartSize() {
        return userManager.getUserCartSize();
    }

    //    MIXED
    public List<Restaurant> getRecommendedRestaurants(int recommendCount) {
        return restaurantManager.getRecommendedRestaurants(userManager.getLocation(), recommendCount);
    }


}
