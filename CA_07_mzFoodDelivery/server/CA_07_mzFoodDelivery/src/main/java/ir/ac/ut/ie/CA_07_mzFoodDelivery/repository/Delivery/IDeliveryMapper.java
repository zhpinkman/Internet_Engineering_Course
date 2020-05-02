package ir.ac.ut.ie.CA_07_mzFoodDelivery.repository.Delivery;

import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.Delivery.Delivery;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.repository.IMapper;

import java.sql.SQLException;
import java.util.List;

public interface IDeliveryMapper extends IMapper<Delivery, String> {
    int getDeliveriesCount() throws SQLException;
    void removeAllDeliveries() throws SQLException;
    List<Delivery> getAllDeliveries() throws SQLException;
}
