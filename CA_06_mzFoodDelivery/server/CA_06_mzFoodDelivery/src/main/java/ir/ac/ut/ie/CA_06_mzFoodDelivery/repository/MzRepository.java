package ir.ac.ut.ie.CA_06_mzFoodDelivery.repository;

import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Food;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Location;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.PartyFood;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Restaurant;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.User.Cart;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.User.CartItem;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.User.User;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.Food.FoodMapper;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.Restaurant.RestaurantMapper;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.User.UserMapper;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.UserCart.UserCartMapper;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.utils.CustomPair;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MzRepository {
    private static MzRepository instance;

    private MzRepository() {
    }

    public static MzRepository getInstance() {
        if (instance == null)
            instance = new MzRepository();
        return instance;
    }

    public void createAllTables() {
        try {
            RestaurantMapper restaurantMapper = new RestaurantMapper(true);
        } catch (Exception ignored) {}
        try {
            FoodMapper foodMapper = new FoodMapper(true);
        } catch (Exception ignored) {}
        try {
            UserMapper userMapper = new UserMapper(true);
        } catch (Exception ignored) {}
        try {
            UserCartMapper userCartMapper = new UserCartMapper(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    RESTAURANT
    public void insertRestaurant(Restaurant restaurant) throws SQLException {
        RestaurantMapper restaurantMapper = new RestaurantMapper();
        restaurantMapper.insert(restaurant);
    }

    public Restaurant findRestaurantById(String restaurantId) throws SQLException {
        return new RestaurantMapper().find(restaurantId);
    }



    //    FOOD
    public void insertFood(Food food) throws SQLException {
        FoodMapper foodMapper = new FoodMapper();
        foodMapper.insert(food);
    }

    public void deleteFood(CustomPair id) throws SQLException {
        new FoodMapper().delete(id);
    }

    public Food getFood(String restaurantId, String foodName) throws SQLException {
        List<String> args = new ArrayList<>();
        args.add(restaurantId);
        args.add(foodName);
        return new FoodMapper().find(new CustomPair(args));
    }

    //    FOOD PART
    public void deletePartyFoods() throws SQLException {
        FoodMapper foodMapper = new FoodMapper();
        foodMapper.deletePartyFoods();
    }

    public List<PartyFood> getPartyFoods() throws SQLException {
        return new FoodMapper().getPartyFoods();
    }

    //    USER
    public void insertUser(User user) throws SQLException {
        new UserMapper().insert(user);
    }

    public User getUser(String email) throws SQLException {
        return new UserMapper().find(email);
    }

    public List<Restaurant> findNearRestaurants(Location location, double maxDistance) throws SQLException {
        return new RestaurantMapper().findNearRestaurants(location, maxDistance);
    }

    public void updateUser(User user) throws SQLException {
        new UserMapper().updateUserCredit(user);
    }

    //      USERCART
    public void insertCartItem(CartItem cartItem) throws SQLException {
        new UserCartMapper().insert(cartItem);
    }

    public void emptyUserCart(String userEmail) throws SQLException {
        new UserCartMapper().emptyUserCart(userEmail);
    }

    public List<CartItem> getUserCart(String userEmail) throws SQLException {
        return new UserCartMapper().getUserCart(userEmail);
    }

    public void updateCartItem(CartItem cartItem) throws SQLException{
        new UserCartMapper().updateCartItem(cartItem);
    }

    public void removeCartItem(CartItem cartItem) throws SQLException {
        List<String> args = new ArrayList<>();
        args.add(cartItem.getUserEmail());
        args.add(cartItem.getRestaurantId());
        args.add(cartItem.getFoodName());
        new UserCartMapper().delete(new CustomPair(args));
    }


    public CartItem findCartItem(CartItem cartItem) throws SQLException {
        List<String> args = new ArrayList<>();
        args.add(cartItem.getUserEmail());
        args.add(cartItem.getRestaurantId());
        args.add(cartItem.getFoodName());
        return new UserCartMapper().find(new CustomPair(args));
    }


}
