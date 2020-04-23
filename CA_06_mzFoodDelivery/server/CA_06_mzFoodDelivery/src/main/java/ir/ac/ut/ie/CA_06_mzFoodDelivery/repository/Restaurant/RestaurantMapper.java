package ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.Restaurant;

import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Location;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Restaurant;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.ConnectionPool;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.Mapper;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.utils.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantMapper extends Mapper<Restaurant, String> implements IRestaurantMapper {

    private static final String COLUMNS = " id, name, locationX, locationY, logo ";
    private static final String TABLE_NAME = "RESTAURANTS";


    public RestaurantMapper(Boolean doManage) throws SQLException {
        if (doManage) {
            Connection con = ConnectionPool.getConnection();
            Statement st = con.createStatement();
            st.executeUpdate(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME));
            st.executeUpdate(String.format(
                    "create table %s (\n" +
                            "    id varchar(255) primary key,\n" +
                            "    name tinytext not null,\n" +
                            "    locationX double not null default 0,\n" +
                            "    locationY double not null default 0,\n" +
                            "    logo text not null\n" +
                            ");",
                    TABLE_NAME));
            st.close();
            con.close();
        }
    }

    public RestaurantMapper() throws SQLException {
    }

    @Override
    protected String getFindStatement(String id) {
        return String.format("select * from %s where %s.%s = %s;", TABLE_NAME, TABLE_NAME, "id", StringUtils.quoteWrapper(id));
    }

    @Override
    protected String getInsertStatement(Restaurant restaurant) {
        return String.format("INSERT INTO %s ( %s ) values (%s, %s, %f, %f, %s);", TABLE_NAME, COLUMNS,
                StringUtils.quoteWrapper(restaurant.getId()), StringUtils.quoteWrapper(restaurant.getName()),
                restaurant.getLocation().getX(), restaurant.getLocation().getY(), StringUtils.quoteWrapper(restaurant.getLogo()));
    }

    @Override
    protected String getDeleteStatement(String id) {
        return String.format("delete from %s where %s.%s = %s", TABLE_NAME, TABLE_NAME, "id", StringUtils.quoteWrapper(id));
    }

    @Override
    protected Restaurant convertResultSetToObject(ResultSet rs) throws SQLException {
        return new Restaurant(
                rs.getString("id"),
                rs.getString("name"),
                "",
                new Location(
                        rs.getDouble("locationX"),
                        rs.getDouble("locationY")
                ),
                null,
                rs.getString("logo")
        );
    }

    @Override
    public List<Restaurant> getAll() throws SQLException {
        List<Restaurant> result = new ArrayList<Restaurant>();
        String statement = "SELECT * FROM " + TABLE_NAME;
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement st = con.prepareStatement(statement);
        ) {
            ResultSet resultSet;
            try {
                resultSet = st.executeQuery();
                while (resultSet.next())
                    result.add(convertResultSetToObject(resultSet));
                return result;
            } catch (SQLException ex) {
                System.out.println("error in Mapper.findAll query.");
                throw ex;
            }
        }
    }

    public List<Restaurant> findNearRestaurants(Location location, double maxDistance, int limit, int offset) throws SQLException {
        List<Restaurant> result = new ArrayList<Restaurant>();
        String statement = "SELECT * FROM " + TABLE_NAME +
                " WHERE sqrt(power(? - locationX, 2) + power(? - locationY, 2)) < ? " +
                "LIMIT ? OFFSET ?";

        Connection con = ConnectionPool.getConnection();
        PreparedStatement st = con.prepareStatement(statement);
        st.setDouble(1, location.getX());
        st.setDouble(2, location.getY());
        st.setDouble(3, maxDistance);
        st.setInt(4, limit);
        st.setInt(5, offset);
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

    public List<Restaurant> search(String searchPhrase, int limit, int offset) throws SQLException{
        List<Restaurant> result = new ArrayList<Restaurant>();
        String statement = "SELECT * FROM " + TABLE_NAME + " WHERE name LIKE '%" + searchPhrase + "%'" +
                " LIMIT " + limit + " OFFSET " + offset;

        try {
            Connection con = ConnectionPool.getConnection();
            PreparedStatement st = con.prepareStatement(statement);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next())
                result.add(convertResultSetToObject(resultSet));

            con.close();
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("error in Mapper.findAll query.");
            throw ex;
        }
    }
}

