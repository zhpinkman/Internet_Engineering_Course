package ir.ac.ut.ie.CA_08_mzFoodDelivery.repository.OrderItem;

import ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.Delivery.OrderItem;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.repository.IMapper;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.utils.CustomPair;

import java.sql.SQLException;
import java.util.List;

public interface IOrderItemMapper extends IMapper<OrderItem, CustomPair> {
    List<OrderItem> getOrderItems(CustomPair id) throws SQLException;
}
