package ir.ac.ut.ie.CA_08_mzFoodDelivery.repository.Order;

import ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.Delivery.Order;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.repository.IMapper;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.utils.CustomPair;

import java.sql.SQLException;
import java.util.List;

public interface IOrderMapper extends IMapper<Order, CustomPair> {
    void updateOrder(Order order) throws SQLException;
    List<Order> getOrders(String userEmail) throws SQLException;
    List<Order> getAllOrders() throws SQLException;
}
