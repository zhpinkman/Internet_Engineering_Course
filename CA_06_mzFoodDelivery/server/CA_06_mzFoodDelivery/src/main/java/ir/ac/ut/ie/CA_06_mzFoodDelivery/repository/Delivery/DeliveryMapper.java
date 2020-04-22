package ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.Delivery;

import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Delivery.Delivery;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Location;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.ConnectionPool;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.Mapper;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.utils.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeliveryMapper extends Mapper<Delivery, String> implements IDeliveryMapper {

    private static final String COLUMNS = " id, velocity, locationX, locationY ";
    private static final String TABLE_NAME = "DELIVERY";

    public DeliveryMapper(Boolean doManage) throws SQLException {
        if (doManage) {
            Connection con = ConnectionPool.getConnection();
            Statement st = con.createStatement();
            st.executeUpdate(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME));
            st.executeUpdate(String.format(
                    "create table %s (\n" +
                            "    id varchar(255) primary key,\n" +
                            "    velocity double not null,\n" +
                            "    locationX double not null,\n" +
                            "    locationY double not null\n" +
                            ");",
                    TABLE_NAME));
            st.close();
            con.close();
        }
    }

    @Override
    protected String getFindStatement(String id) {
        return String.format("select * from %s where %s = %s;", TABLE_NAME, "id", StringUtils.quoteWrapper(id));
    }

    @Override
    protected String getInsertStatement(Delivery delivery) {
        return String.format("insert into %s ( %s ) values (%s, %f, %f, %f);", TABLE_NAME, COLUMNS,
                StringUtils.quoteWrapper(delivery.getId()), delivery.getVelocity(), delivery.getLocation().getX(), delivery.getLocation().getY());
    }

    @Override
    protected String getDeleteStatement(String id) {
        return String.format("delete from %s where %s = %s;", TABLE_NAME, "id", StringUtils.quoteWrapper(id));
    }

    @Override
    protected Delivery convertResultSetToObject(ResultSet rs) throws SQLException {
        return new Delivery(
                rs.getString("id"),
                rs.getDouble("velocity"),
                new Location(
                        rs.getDouble("locationX"),
                        rs.getDouble("locationY")
                )
        );
    }
}
