package ir.ac.ut.ie.CA_07_mzFoodDelivery.repository.Restaurant;

import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Restaurant;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.User.User;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.repository.IMapper;

import java.sql.SQLException;
import java.util.List;

public interface IRestaurantMapper extends IMapper<Restaurant, String> {
        List<Restaurant> getAll() throws SQLException;
}
