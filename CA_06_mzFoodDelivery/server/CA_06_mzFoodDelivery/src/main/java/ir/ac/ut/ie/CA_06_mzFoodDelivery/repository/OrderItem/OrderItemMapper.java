package ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.OrderItem;

import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Delivery.OrderItem;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.PartyFood;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.ConnectionPool;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.Mapper;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.utils.CustomPair;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.utils.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemMapper extends Mapper<OrderItem, CustomPair> implements IOrderItemMapper {

    private static final String COLUMNS = " userEmail, orderId, restaurantId, foodName, quantity ";
    private static final String TABLE_NAME = "ORDERITEMS";

    public OrderItemMapper(Boolean doManage) throws SQLException {
        if (doManage) {
            Connection con = ConnectionPool.getConnection();
            Statement st = con.createStatement();
            st.executeUpdate(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME));
            st.executeUpdate(String.format(
                    "create table %s (\n" +
                            "    userEmail varchar(255),\n" +
                            "    orderId int,\n" +
                            "    restaurantId varchar(255),\n" +
                            "    foodName varchar(255), \n" +
                            "    quantity int not null,\n" +
                            "    primary key (userEmail, orderId, restaurantId, foodName)\n" +
                            ");",
                    TABLE_NAME));
            st.close();
            con.close();
        }
    }


    public OrderItemMapper() {}


    @Override
    protected String getFindStatement(CustomPair id) {
        return String.format("select * from %s where %s = %s and %s = %d and %s = %s and %s = %s;", TABLE_NAME,
                "userEmail", StringUtils.quoteWrapper(id.getArgs().get(0)),
                "orderId", Integer.parseInt(id.getArgs().get(1)),
                "restaurantid", StringUtils.quoteWrapper(id.getArgs().get(2)),
                "foodName", StringUtils.quoteWrapper(id.getArgs().get(3)));
    }

    @Override
    protected String getInsertStatement(OrderItem orderItem) {
        System.out.println(String.format("insert into %s ( %s ) values (%s, %d, %s, %s, %d);", TABLE_NAME, COLUMNS,
                StringUtils.quoteWrapper(orderItem.getUserEmail()),
                orderItem.getId(),
                StringUtils.quoteWrapper(orderItem.getRestaurantId()),
                StringUtils.quoteWrapper(orderItem.getFoodName()),
                orderItem.getQuantity()));
        return String.format("insert into %s ( %s ) values (%s, %d, %s, %s, %d);", TABLE_NAME, COLUMNS,
                StringUtils.quoteWrapper(orderItem.getUserEmail()),
                orderItem.getId(),
                StringUtils.quoteWrapper(orderItem.getRestaurantId()),
                StringUtils.quoteWrapper(orderItem.getFoodName()),
                orderItem.getQuantity());
    }

    @Override
    protected String getDeleteStatement(CustomPair id) {
        return null;
    }

    @Override
    protected OrderItem convertResultSetToObject(ResultSet rs) throws SQLException {
        return new OrderItem(
                rs.getString("userEmail"),
                rs.getInt("orderId"),
                rs.getString("restaurantId"),
                rs.getString("foodName"),
                rs.getInt("quantity")
        );
    }

    @Override
    public List<OrderItem> getOrderItems(CustomPair id) throws SQLException {
        List<OrderItem> result = new ArrayList<OrderItem>();
        String statement = String.format("select * from %s where %s = %s and %s = %s", TABLE_NAME,
                "userEmail", StringUtils.quoteWrapper(id.getArgs().get(0)),
                "orderId", Integer.parseInt(id.getArgs().get(1)));
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement st = con.prepareStatement(statement);
        ) {
            ResultSet resultSet;
            try {
                resultSet = st.executeQuery();
                while (resultSet.next())
                    result.add(convertResultSetToObject(resultSet));
                con.close();
                return result;
            } catch (SQLException ex) {
                System.out.println("error in Mapper.findAll query.");
                throw ex;
            }
        }
    }
}
