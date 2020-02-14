package MzFoodDelivery.User;

import MzFoodDelivery.Restaurant.Location;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class UserManager {
    private User user = new User("Ehsan", "Khames", "ekhamespanah@yahoo.com", "989123456789", new Location(0, 0));


    public void addToCart(CartItem cartItem) throws Exception {
        user.addToCart(cartItem);
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

    public void finalizeOrder() {
        user.finalizeOrder();
    }

    public int getUserCartSize() {
        return user.getUserCartSize();
    }

    public User getUser() {
        return user;
    }

    public void chargeUserCredit(double amount) {
        user.chargeUserCredit(amount);
    }
}
