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
        System.out.println("mohsen here");
        User user = new User("Ehsan", "Khames", "ekhamespanah@yahoo.com", "989123456789", new Location(0, 0));
        try {
            MzRepository.getInstance().insertUser(user);
            System.out.println("zhivar here");
        } catch (SQLException ignored) {}
    }

    public void addToCart(CartItem cartItem) throws Exception {
//        user.addToCart(cartItem);
    }

    public Location getLocation(String email) throws SQLException{
        return MzRepository.getInstance().getUser(email).getLocation();
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

    public boolean foodRepoEmpty(Cart cart) throws SQLException {

        for (CartItem cartItem : cart.getCartItems()) {
            Food food = MzRepository.getInstance().getFood(cartItem.getRestaurantId(), cartItem.getFoodName());
            if (!food.hasEnoughAmount(cartItem.getQuantity()))
                return true;
        }
        return false;
    }

    public int getUserCartSize() throws SQLException {
        return user.getUserCartSize();
    }

    public User getUser(String email) throws SQLException{
        return MzRepository.getInstance().getUser(email);
    }

    public void chargeUserCredit(double amount) throws Exception {
        user.chargeUserCredit(amount);
    }

    public void deleteFromCart(String restaurantId, String foodName) throws Exception {
        user.deleteFromCart(restaurantId, foodName);
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
