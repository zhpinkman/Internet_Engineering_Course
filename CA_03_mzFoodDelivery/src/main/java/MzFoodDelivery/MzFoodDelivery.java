package MzFoodDelivery;

import MzFoodDelivery.Restaurant.Food;
import MzFoodDelivery.Restaurant.Restaurant;
import MzFoodDelivery.Restaurant.RestaurantManager;
import MzFoodDelivery.User.Cart;
import MzFoodDelivery.User.CartItem;
import MzFoodDelivery.User.User;
import MzFoodDelivery.User.UserManager;

import java.util.ArrayList;
import java.util.List;

public class MzFoodDelivery {

    private static MzFoodDelivery instance;

    private RestaurantManager restaurantManager = new RestaurantManager();
    private UserManager userManager = new UserManager();
    private List<Delivery> deliveries = new ArrayList<Delivery>();


    private MzFoodDelivery() {}

    public static MzFoodDelivery getInstance() {
        if (instance == null) {
            instance = new MzFoodDelivery();
        }
        return instance;
    }

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
        CartItem cartItem = new CartItem(restaurant, restaurant.getFood(foodName));
        userManager.addToCart(cartItem);
    }

    public Cart getCart() {
        return userManager.getCart();
    }

    public String getBriefCartJson() {
        return userManager.getBriefCartJson();
    }

    public void finalizeOrder() throws Exception {
        userManager.finalizeOrder();
    }

    public int getUserCartSize() {
        return userManager.getUserCartSize();
    }

    public void chargeUserCredit(double amount) {
        userManager.chargeUserCredit(amount);
    }

    //    MIXED
    public List<Restaurant> getRecommendedRestaurants(int recommendCount) {
        return restaurantManager.getRecommendedRestaurants(userManager.getLocation(), recommendCount);
    }

    public List<Restaurant> getNearRestaurants(){
        return restaurantManager.getNearRestaurants(userManager.getLocation());
    }

    public Restaurant getNearRestaurantById(String id) throws Exception{
        return restaurantManager.getNearRestaurantById(id, userManager.getLocation());
    }

    public User getUser() {
        return userManager.getUser();
    }

    public void addToCartByRestaurantId(String restaurantId, String foodName) throws Exception {
        String restaurantName = getRestaurantById(restaurantId);
        addToCart(restaurantName, foodName);
    }

    private String getRestaurantById(String restaurantId) throws Exception {
        return restaurantManager.getRestaurantNameById(restaurantId);
    }

    public void addDelivery(Delivery delivery) {
        deliveries.add(delivery);
    }


    public List<Delivery> getDeliveries() {
        return deliveries;
    }
}
