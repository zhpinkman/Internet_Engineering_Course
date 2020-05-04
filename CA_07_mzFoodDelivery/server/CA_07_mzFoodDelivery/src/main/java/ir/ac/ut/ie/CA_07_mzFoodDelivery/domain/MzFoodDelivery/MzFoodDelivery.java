package ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery;


import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.Delivery.Delivery;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.Delivery.Order;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.FoodParty.FoodPartyManager;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Food;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.PartyFood;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Restaurant;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.RestaurantManager;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.User.CartItem;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.User.User;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.User.UserManager;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.repository.MzRepository;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.utils.schedulers.BackgroundJobManager;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MzFoodDelivery {

    private static MzFoodDelivery instance;

    private RestaurantManager restaurantManager = new RestaurantManager();
    private UserManager userManager = new UserManager();
    private FoodPartyManager foodPartyManager = new FoodPartyManager();
    private int foodPartyPeriod;
    private long foodPartyStartTime;


    private MzFoodDelivery() {
    }

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

    public void addPartyFood(String restaurantName, PartyFood partyFood) throws Exception {
        restaurantManager.addFood(restaurantName, partyFood);
        foodPartyManager.addFood(partyFood);
    }

    public void printRestaurants() {
        restaurantManager.printRestaurants();
    }

    public Restaurant getRestaurant(String restaurantName) throws Exception {
        return restaurantManager.getRestaurant(restaurantName);
    }

    public Restaurant getRestaurantById(String restaurantId) throws Exception {
        Restaurant r = restaurantManager.findRestaurantById(restaurantId);
        if (r == null) {
            throw new Exception("Error: restaurant does not exists");
        } else {
            return r;
        }
    }

    public Food getFood(String restaurantName, String foodName) throws Exception {
        return restaurantManager.getFood(restaurantName, foodName);
    }

    //    USER MANAGER
    public void addToCart(String restaurantId, String foodName) throws Exception {
        CartItem cartItem = new CartItem(SecurityContextHolder.getContext().getAuthentication().getName(), restaurantId, foodName);
        userManager.addToCart(cartItem);
    }

    public synchronized void addToCart(String restaurantId, String foodName, int amount) throws Exception {
        Food food = MzRepository.getInstance().getFood(restaurantId, foodName);

        if (food instanceof PartyFood) {
            PartyFood partyFood = (PartyFood) food;
            if (partyFood.getCount() < amount) {
                throw new Exception("not enough food in inventory");
            }
        }

        CartItem cartItem = new CartItem(SecurityContextHolder.getContext().getAuthentication().getName(), restaurantId, foodName, amount);
        userManager.addToCart(cartItem);

    }

    public void deleteFromCart(String restaurantId, String foodName) throws Exception {
        Restaurant restaurant = getRestaurantById(restaurantId);
        Food food = restaurant.getFood(foodName);
        userManager.deleteFromCart(restaurantId, foodName);
    }

    public List<Order> getOrders() throws SQLException {
        return userManager.getOrders();
    }

    public List<CartItem> getCart() throws SQLException {
        return userManager.getCart();
    }

    public String getBriefCartJson() throws SQLException {
        return userManager.getBriefCartJson();
    }

    public void finalizeOrder() throws Exception {
        List<CartItem> cartItems = userManager.finalizeOrder();
        System.out.println("finalized");
        restaurantManager.decreaseFoodAmounts(cartItems);
        System.out.println("decreased");
        BackgroundJobManager.startJob();
    }

    public int getUserCartSize() throws SQLException {
        return userManager.getUserCartSize();
    }

    public void chargeUserCredit(double amount) throws Exception {
        userManager.chargeUserCredit(amount);
    }

    //    MIXED
//    public List<Restaurant> getRecommendedRestaurants(int recommendCount) {
//        try {
//            return restaurantManager.getRecommendedRestaurants(userManager.getLocation(userEmail), recommendCount);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public List<Restaurant> getNearRestaurants(int limit, int offset) {
        try {
            return restaurantManager.getNearRestaurants(userManager.getLocation(SecurityContextHolder.getContext().getAuthentication().getName()), limit, offset);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Restaurant getNearRestaurantById(String id) throws Exception {
        return restaurantManager.getNearRestaurantById(id, userManager.getLocation(SecurityContextHolder.getContext().getAuthentication().getName()));
    }

    public User getUser(String userEmail) throws SQLException {
        return userManager.getUser(userEmail);
    }

    public User getUser() throws SQLException {
        return userManager.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public void addToCartByRestaurantId(String restaurantId, String foodName) throws Exception {
        String restaurantName = getRestaurantNameById(restaurantId);
        addToCart(restaurantName, foodName);
    }

    private String getRestaurantNameById(String restaurantId) throws Exception {
        return restaurantManager.getRestaurantNameById(restaurantId);
    }


    public void assignDeliveryToOrder() throws SQLException {
        Order latestOrder = userManager.getLatestOrder();
        try {
            Delivery delivery = getQuickestDelivery(latestOrder);
            latestOrder.setDelivery(delivery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Delivery getQuickestDelivery(Order order) throws SQLException {
        double minTime = Double.POSITIVE_INFINITY;
        Delivery quickestDelivery = null;
        List<Delivery> deliveries = MzRepository.getInstance().getDeliveries();
        for (Delivery delivery : deliveries) {
            Restaurant restaurant = order.getOrderRestaurant();
            double distanceToDeliverOrder = calcDeliveryDistanceToGo(restaurant, delivery);
            double timeToDeliverOrder = distanceToDeliverOrder / delivery.getVelocity();
            if (timeToDeliverOrder < minTime) {
                quickestDelivery = delivery;
            }
        }
        MzRepository.getInstance().removeDelivery(quickestDelivery.getId());
        return quickestDelivery;
    }

    public double calcDeliveryDistanceToGo(Restaurant restaurant, Delivery delivery) throws SQLException {
        double distanceToGetToRestaurant = delivery.getLocation().getDistanceFromLocation(restaurant.getLocation());
        double distanceToGetToCustomer = delivery.getLocation().getDistanceFromLocation(getUser(SecurityContextHolder.getContext().getAuthentication().getName()).getLocation());
        return distanceToGetToCustomer + distanceToGetToRestaurant;
    }

    public void importFoodPartyFromWeb() throws Exception {
        if (SecurityContextHolder.getContext().getAuthentication() == null) return;
        removeItemsInCart();
        foodPartyManager.importFoodPartyFromWeb();
    }

    private void removeItemsInCart() throws SQLException {
        for (CartItem cartItem : userManager.getCart()) {
            Food food = MzRepository.getInstance().getFood(cartItem.getRestaurantId(), cartItem.getFoodName());
            if (food instanceof PartyFood)
                MzRepository.getInstance().removeCartItem(cartItem);
        }
    }

    public List<PartyFood> getPartyFoods() {
        try {
            return foodPartyManager.getPartyFoods();
        } catch (Exception e) {
            return new ArrayList<PartyFood>();
        }
    }

    public void setFoodPartyPeriod(int period) {
        this.foodPartyPeriod = period;
    }

    public void resetFoodPartyTimer() {
        this.foodPartyStartTime = System.currentTimeMillis();
    }

    public long getFoodPartyRemainingTime() {
        return this.foodPartyPeriod - (System.currentTimeMillis() - this.foodPartyStartTime) / 1000;
    }

    public List<Restaurant> searchRestaurants(String searchPhrase, int limit, int offset) {
        return MzRepository.getInstance().searchRestaurants(searchPhrase, limit, offset);
    }

    public List<Restaurant> searchFoods(String searchPhrase, int limit, int offset) {
        return MzRepository.getInstance().searchFoods(searchPhrase, limit, offset);
    }

    public int getDeliveriesCount() throws SQLException {
        return MzRepository.getInstance().getDeliveriesCount();
    }

    public void addUser(String email, String firstName, String lastName, String password) throws Exception {
        userManager.addUser(email, firstName, lastName, password);
    }

    public User loginUser(String email, String password) throws Exception {
        return userManager.loginUser(email, password);
    }
}
