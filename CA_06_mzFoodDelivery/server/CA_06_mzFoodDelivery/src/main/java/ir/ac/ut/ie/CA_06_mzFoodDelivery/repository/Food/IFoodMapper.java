package ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.Food;

import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Food;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.IMapper;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.utils.CustomPair;

import java.sql.SQLException;
import java.util.List;


public interface IFoodMapper extends IMapper<Food, CustomPair> {
    public void deletePartyFoods() throws SQLException;
    public List<Food> getRestaurantMenu(String restaurantId) throws SQLException;
}
