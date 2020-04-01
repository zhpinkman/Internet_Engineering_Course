package ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery;


import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.Delivery.Delivery;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.Delivery.Order;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.FoodParty.FoodPartyManager;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Food;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.PartyFood;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Restaurant;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.RestaurantManager;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.User.Cart;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.User.CartItem;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.User.User;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.User.UserManager;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.utils.schedulers.BackgroundJobManager;

import java.util.ArrayList;
import java.util.List;

public class MzFoodDelivery {

    private static MzFoodDelivery instance;

    private RestaurantManager restaurantManager = new RestaurantManager();
    private UserManager userManager = new UserManager();
    private FoodPartyManager foodPartyManager = new FoodPartyManager();
    private List<Delivery> deliveries = new ArrayList<Delivery>();
    private List<Order> orderList = new ArrayList<Order>();
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
        return restaurantManager.findRestaurantById(restaurantId);
    }

    public Food getFood(String restaurantName, String foodName) throws Exception {
        return restaurantManager.getFood(restaurantName, foodName);
    }

    //    USER MANAGER
    public void addToCart(String restaurantId, String foodName) throws Exception {
        Restaurant restaurant = getRestaurantById(restaurantId);
        Food food = restaurant.getFood(foodName);
        CartItem cartItem = new CartItem(restaurant, restaurant.getFood(foodName));
        userManager.addToCart(cartItem);
    }

    public synchronized void addToCart(String restaurantId, String foodName, int amount) throws Exception {
        Restaurant restaurant = getRestaurantById(restaurantId);
        PartyFood partyFood = (PartyFood) restaurant.getFood(foodName);
        if (partyFood.getCount() < amount)
            throw new Exception();
        else {
            for (int i = 0; i < amount; i++) {
                CartItem cartItem = new CartItem(restaurant, restaurant.getFood(foodName));
                userManager.addToCart(cartItem);
            }
        }
    }

    public Order getOrderById(double id) throws Exception {
        for (Order order : orderList) {
            if (order.getId() == id) {
                return order;
            }
        }
        throw new Exception("orderId not found");
    }

    public List<Order> getOrders() {
        return orderList;
    }

    public Cart getCart() {
        return userManager.getCart();
    }

    public String getBriefCartJson() {
        return userManager.getBriefCartJson();
    }

    public void finalizeOrder() throws Exception {
        Order order = userManager.finalizeOrder();
        restaurantManager.decreaseFoodAmounts(order);
        orderList.add(order);
        BackgroundJobManager.startJob();
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

    public List<Restaurant> getNearRestaurants() {
        return restaurantManager.getNearRestaurants(userManager.getLocation());
    }

    public Restaurant getNearRestaurantById(String id) throws Exception {
        return restaurantManager.getNearRestaurantById(id, userManager.getLocation());
    }

    public User getUser() {
        return userManager.getUser();
    }

    public void addToCartByRestaurantId(String restaurantId, String foodName) throws Exception {
        String restaurantName = getRestaurantNameById(restaurantId);
        addToCart(restaurantName, foodName);
    }

    private String getRestaurantNameById(String restaurantId) throws Exception {
        return restaurantManager.getRestaurantNameById(restaurantId);
    }

    public void removeDeliveries() {
        deliveries.clear();
    }

    public void addDeliveries(List<Delivery> deliveryList) {
        deliveries.addAll(deliveryList);
    }


    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void assignDeliveryToOrder() {
        Order latestOrder = orderList.get(orderList.size() - 1);
        Delivery delivery = getQuickestDelivery(latestOrder);
        latestOrder.setDelivery(delivery);
    }

    public Delivery getQuickestDelivery(Order order) {
        double minTime = Double.POSITIVE_INFINITY;
        Delivery quickestDelivery = null;
        for (Delivery delivery : deliveries) {
            double distanceToDeliverOrder = calcDeliveryDistanceToGo(order.getCart().getRestaurant(), delivery);
            double timeToDeliverOrder = distanceToDeliverOrder / delivery.getVelocity();
            if (timeToDeliverOrder < minTime) {
                quickestDelivery = delivery;
            }
        }
        return quickestDelivery;
    }

    public double calcDeliveryDistanceToGo(Restaurant restaurant, Delivery delivery) {
        double distanceToGetToRestaurant = delivery.getLocation().getDistanceFromLocation(restaurant.getLocation());
        double distanceToGetToCustomer = delivery.getLocation().getDistanceFromLocation(getUser().getLocation());
        return distanceToGetToCustomer + distanceToGetToRestaurant;
    }

    public void importFoodPartyFromWeb() throws Exception {
        removeOlderOffers();
        foodPartyManager.importFoodPartyFromWeb();
    }

    private void removeOlderOffers() {
        for (CartItem cartItem : userManager.getCart().getCartItems()) {
            if (cartItem.getFood() instanceof PartyFood)
                userManager.getCart().removeCartItem((PartyFood) cartItem.getFood());
        }
    }

    public List<PartyFood> getPartyFoods() {
        return foodPartyManager.getPartyFoods();
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
}
