package ir.ac.ut.ie.CA_08_mzFoodDelivery.repository.Restaurant;

import ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Restaurant;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.User.User;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.repository.IMapper;

import java.sql.SQLException;
import java.util.List;

public interface IRestaurantMapper extends IMapper<Restaurant, String> {
        List<Restaurant> getAll() throws SQLException;
}
