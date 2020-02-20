package services;

import HTTPRequestHandler.HTTPRequsestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import services.Restaurant.Food;
import services.Restaurant.Restaurant;
import services.Restaurant.RestaurantManager;
import services.User.Cart;
import services.User.CartItem;
import services.User.User;
import services.User.UserManager;

import java.util.List;

public class MzFoodDelivery {

    private static MzFoodDelivery instance;

    private MzFoodDelivery(){

    }

    public static MzFoodDelivery getInstance() {
        if (instance == null) {
            instance = new MzFoodDelivery();
        }
        return instance;
    }

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

    public void importRestaurantsFromWeb(String uri) throws Exception {
        String RestaurantsJsonString = HTTPRequsestHandler.getRequest("http://138.197.181.131:8080/restaurants");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Restaurant> restaurants = gson.fromJson(RestaurantsJsonString, new TypeToken<List<Restaurant>>() {
        }.getType());
        int counter = 1;
        for (Restaurant restaurant : restaurants) {
            System.out.println(counter + "----------------");
            counter++;
            restaurant.print();
            try {
                addRestaurant(restaurant);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
