package ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.UserCart;

import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.User.CartItem;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.IMapper;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.utils.CustomPair;

import java.sql.SQLException;
import java.util.List;

public interface IUserCartMapper extends IMapper<CartItem, CustomPair> {
    List<CartItem> getUserCart(String userEmail) throws SQLException;
    void emptyUserCart(String userEmail) throws SQLException;
    void updateCartItem(CartItem cartItem) throws SQLException;
}
