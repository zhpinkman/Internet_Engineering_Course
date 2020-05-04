package ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.Delivery.Order;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Food;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Location;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.repository.MzRepository;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.SQLException;
import java.util.List;

public class UserManager {



    public UserManager() {
//        System.out.println("mohsen here");
//        User user = new User("Ehsan", "Khames", "ekhamespanah@yahoo.com", "1234","989123456789", new Location(0, 0), 0, 0);
//        try {
//            MzRepository.getInstance().insertUser(user);
////            System.out.println("zhivar here");
//        } catch (SQLException ignored) {}
    }

    public void addToCart(CartItem cartItem) throws Exception {
        User user = MzRepository.getInstance().getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        user.addToCart(cartItem);
    }

    public Location getLocation(String email) throws SQLException{
        return MzRepository.getInstance().getUser(email).getLocation();
    }

    public List<CartItem> getCart() throws SQLException {
        if (SecurityContextHolder.getContext().getAuthentication().getName().isEmpty()) return null;
        User user = MzRepository.getInstance().getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        return user.getUserCart();
    }

    public String getBriefCartJson() throws SQLException {
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

    public List<CartItem> finalizeOrder() throws Exception {
        User user = MzRepository.getInstance().getUser(SecurityContextHolder.getContext().getAuthentication().getName());
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

    public boolean foodRepoEmpty(List<CartItem> cartItems) throws SQLException {
        for (CartItem cartItem : cartItems) {
            Food food = MzRepository.getInstance().getFood(cartItem.getRestaurantId(), cartItem.getFoodName());
            if (!food.hasEnoughAmount(cartItem.getQuantity()))
                return true;
        }
        return false;
    }

    public int getUserCartSize() throws SQLException {
        User user = MzRepository.getInstance().getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        return user.getUserCartSize();
    }

    public User getUser(String email) throws SQLException{
        return MzRepository.getInstance().getUser(email);
    }

    public void chargeUserCredit(double amount) throws Exception {
        User user = MzRepository.getInstance().getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        user.chargeUserCredit(amount);
    }

    public void deleteFromCart(String restaurantId, String foodName) throws Exception {
        User user = MzRepository.getInstance().getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        user.deleteFromCart(restaurantId, foodName);
    }

    public Order getLatestOrder() throws SQLException {
        User user = MzRepository.getInstance().getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        int orderId = user.getNumOfOrders();
        System.out.println("num of orders");
        System.out.println(orderId);
        return MzRepository.getInstance().getOrder(SecurityContextHolder.getContext().getAuthentication().getName(), orderId);
    }

    public List<Order> getOrders() throws SQLException {
        return MzRepository.getInstance().getOrders(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public void addUser(String email, String firstName, String lastName, String password) throws Exception {
        try {
            User user = new User(firstName, lastName, email, password, "", new Location(0, 0), 0, 0);
            MzRepository.getInstance().insertUser(user);
        } catch (SQLException e) {
            throw new Exception("email is already taken");
        }
    }

    public User loginUser(String email, String password) throws Exception {
        try {
            User user = MzRepository.getInstance().getUser(email);
            return user;
        } catch (SQLException e) {
            throw new Exception("user not available");
        }
    }
}
