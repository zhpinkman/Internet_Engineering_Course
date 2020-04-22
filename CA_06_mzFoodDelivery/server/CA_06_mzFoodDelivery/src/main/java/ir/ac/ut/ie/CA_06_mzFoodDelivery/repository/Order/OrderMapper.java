package ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.Order;

import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Delivery.Order;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.ConnectionPool;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.Mapper;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.utils.CustomPair;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.utils.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
                            "    startingDeliveryTime DATE,\n" +
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
        return String.format("select * from %s where %s = %s and %s = %d", TABLE_NAME,
                "userEmail", StringUtils.quoteWrapper(id.getArgs().get(0)),
                "orderId", Integer.parseInt(id.getArgs().get(1)));
    }

    @Override
    protected String getInsertStatement(Order order) {
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
                rs.getDate("startingDeliveryTime")
        );
    }
}
