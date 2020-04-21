package ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Delivery.Order;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Food;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Location;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Restaurant;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.MzRepository;

import java.sql.SQLException;
import java.util.List;

public class UserManager {

    private User user = new User("Ehsan", "Khames", "ekhamespanah@yahoo.com", "989123456789", new Location(0, 0));

    public UserManager() {
        User user = new User("Ehsan", "Khames", "ekhamespanah@yahoo.com", "989123456789", new Location(0, 0));
        try {
            MzRepository.getInstance().insertUser(user);
        } catch (SQLException ignored) {}
    }

    public void addToCart(CartItem cartItem) throws Exception {
//        user.addToCart(cartItem);
    }

    public Location getLocation() {
        return user.getLocation();
    }

    public Cart getCart() {
        return user.getUserCart();
    }

    public String getBriefCartJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement cartJsonElement = gson.toJsonTree(this.getCart());
        JsonArray array = cartJsonElement.getAsJsonObject().get("cartItems").getAsJsonArray();
        array.forEach(item -> {
            item.getAsJsonObject().addProperty("foodName", item.getAsJsonObject().get("food").getAsJsonObject().get("name").getAsString());
            item.getAsJsonObject().remove("food");
            item.getAsJsonObject().remove("restaurant");
        });
        return gson.toJson(cartJsonElement);
    }

    public Order finalizeOrder() throws Exception {
        double totalPrice = user.getCartTotalPrice();
        if (user.getUserCartSize() == 0)
            throw new Exception("user cart is empty");
        if (totalPrice > user.getCredit())
            throw new Exception("credit is not enough for finalizing your order");
        if (foodRepoEmpty(user.getUserCart()))
            throw new Exception("count of this offer is not enough for you to submit");
        user.withdrawCredit(totalPrice);
        return user.finalizeOrder();
    }

    public boolean foodRepoEmpty(Cart cart) {
        for (CartItem cartItem : cart.getCartItems()) {
            if (!cartItem.getFood().hasEnoughAmount(cartItem.getQuantity()))
                return true;
        }
        return false;
    }

    public int getUserCartSize() {
        return user.getUserCartSize();
    }

    public User getUser() {
        return user;
    }

    public void chargeUserCredit(double amount) throws Exception {
        user.chargeUserCredit(amount);
    }

    public void deleteFromCart(Restaurant restaurant, Food food) throws Exception {
        user.deleteFromCart(restaurant, food);
    }

    public Order getOrderById(double id) throws Exception {
        return user.getOrderById(id);
    }

    public List<Order> getOrders() {
        return user.getOrders();
    }

    public void addOrder(Order order) {
        user.addOrder(order);
    }

    public Order getLatestOrder() {
        return user.getLatestOrder();
    }
}
