package ir.ac.ut.ie.CA_06_mzFoodDelivery.repository;

import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Food;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.PartyFood;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Restaurant;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.User.User;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.Food.FoodMapper;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.Restaurant.RestaurantMapper;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.User.UserMapper;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.utils.CustomPair;

import java.sql.SQLException;
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
}
