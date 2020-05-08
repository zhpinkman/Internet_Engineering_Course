package ir.ac.ut.ie.CA_07_mzFoodDelivery.repository.Order;

import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.Delivery.Delivery;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.Delivery.Order;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.repository.ConnectionPool;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.repository.Mapper;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.utils.CustomPair;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.utils.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper extends Mapper<Order, CustomPair> implements IOrderMapper {

    private static final String COLUMNS = " userEmail, orderId ";
    private static final String TABLE_NAME = "ORDERS";

    public OrderMapper(Boolean doManage) throws SQLException {
        if (doManage) {
            Connection con = ConnectionPool.getConnection();
            Statement st = con.createStatement();
            st.executeUpdate(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME));
            st.executeUpdate(String.format(
                    "create table %s (\n" +
                            "    userEmail varchar(255) not null,\n" +
                            "    orderId int not null,\n" +
                            "    status varchar(255) not null default 'SEARCHING',\n" +
                            "    deliveryId varchar(255),\n" +
                            "    startingDeliveryTime varchar(255),\n" +
                            "    primary key (userEmail, orderId)\n" +
                            ");",
                    TABLE_NAME));
            st.close();
            con.close();
        }
    }


    public OrderMapper() {}

    @Override
    protected String getFindStatement(CustomPair id) {
        System.out.println(String.format("select * from %s where %s = %s and %s = %d;", TABLE_NAME,
                "userEmail", StringUtils.quoteWrapper(id.getArgs().get(0)),
                "orderId", Integer.parseInt(id.getArgs().get(1))));
        return String.format("select * from %s where %s = %s and %s = %d;", TABLE_NAME,
                "userEmail", StringUtils.quoteWrapper(id.getArgs().get(0)),
                "orderId", Integer.parseInt(id.getArgs().get(1)));
    }

    @Override
    protected String getInsertStatement(Order order) {
        System.out.println(String.format("insert into %s ( %s ) values (%s, %d);", TABLE_NAME, COLUMNS,
                StringUtils.quoteWrapper(order.getUserEmail()), order.getId()));
        return String.format("insert into %s ( %s ) values (%s, %d);", TABLE_NAME, COLUMNS,
                StringUtils.quoteWrapper(order.getUserEmail()), order.getId());
    }

    @Override
    protected String getDeleteStatement(CustomPair id) {
        return null;
    }

    @Override
    protected Order convertResultSetToObject(ResultSet rs) throws SQLException {
        return new Order(
                rs.getString("userEmail"),
                rs.getInt("orderId"),
                rs.getString("status"),
                rs.getString("deliveryId"),
                StringUtils.convertToLocalDateTime(rs.getString("startingDeliveryTime"))
        );
    }

    @Override
    public void updateOrder(Order order) throws SQLException {

        String statement = String.format("update %s set %s = %s, %s = %s, %s = %s where %s = %s and %s = %d;", TABLE_NAME,
                "status", StringUtils.quoteWrapper(order.getStatusString()),
                "deliveryId", StringUtils.quoteWrapper(order.getDeliveryId()),
                "startingDeliveryTime", StringUtils.quoteWrapper(StringUtils.convertToString(order.getStartingDeliveryTime())),
                "userEmail", StringUtils.quoteWrapper(order.getUserEmail()),
                "orderId", order.getId());
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement st = con.prepareStatement(statement);
        ) {
            try {
                st.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("error in Mapper.updateCartItem query.");
                throw ex;
            }
        }
    }

    @Override
    public List<Order> getOrders(String userEmail) throws SQLException {
        List<Order> result = new ArrayList<Order>();
        String statement = String.format("SELECT * from %s where %s = %s", TABLE_NAME, "userEmail", StringUtils.quoteWrapper(userEmail));
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

    @Override
    public List<Order> getAllOrders() throws SQLException {
        List<Order> result = new ArrayList<Order>();
        String statement = String.format("SELECT * from %s;", TABLE_NAME);
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
