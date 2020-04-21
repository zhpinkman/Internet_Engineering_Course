package ir.ac.ut.ie.CA_06_mzFoodDelivery.repository;

import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Food;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Restaurant;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.Food.FoodMapper;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.Restaurant.RestaurantMapper;

import java.sql.SQLException;

public class MzRepository {
    private static MzRepository instance;

    private MzRepository() {

    }

    public static MzRepository getInstance() {
        if (instance == null)
            instance = new MzRepository();
        return instance;
    }

    public void insertRestaurant(Restaurant restaurant) throws SQLException{
        RestaurantMapper restaurantMapper = new RestaurantMapper();
        restaurantMapper.insert(restaurant);
    }

    public void insertFood(Food food) throws SQLException{
        FoodMapper foodMapper = new FoodMapper();
        foodMapper.insert(food);
    }
}
