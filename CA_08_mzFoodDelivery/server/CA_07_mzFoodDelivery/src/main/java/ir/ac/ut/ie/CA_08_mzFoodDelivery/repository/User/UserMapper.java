package ir.ac.ut.ie.CA_08_mzFoodDelivery.repository.User;


import ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Location;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.User.User;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.repository.ConnectionPool;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.repository.Mapper;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.utils.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserMapper extends Mapper<User, String> implements IUserMapper {

    private static final String COLUMNS = " email, password, firstName, lastName, phoneNumber, locationX, locationY ";
    private static final String TABLE_NAME = "USERS";


    public UserMapper(Boolean doManage) throws SQLException {
        if (doManage) {
            Connection con = ConnectionPool.getConnection();
            Statement st = con.createStatement();
            st.executeUpdate(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME));
            st.executeUpdate(String.format(
                    "create table %s (\n" +
                            "   email varchar(255) primary key,\n" +
                            "   password varchar(255) not null,\n" +
                            "   firstName tinytext not null,\n" +
                            "   lastName tinytext not null,\n" +
                            "   credit double not null default 0,\n" +
                            "   orders int not null default 0,\n" +
                            "   phoneNumber tinytext,\n" +
                            "   locationX double not null default 0,\n" +
                            "   locationY double not null default 0\n" +
                            ");",
                    TABLE_NAME));
            st.close();
            con.close();
        }
    }

    public UserMapper() throws SQLException {
    }

    @Override
    protected String getFindStatement(String id) {
        return String.format("select * from %s where %s.%s = %s;", TABLE_NAME, TABLE_NAME, "email", StringUtils.quoteWrapper(id));
    }

    @Override
    protected String getInsertStatement(User user) {
        return String.format("INSERT INTO %s ( %s ) values (%s, %s, %s, %s, %s, %f, %f);", TABLE_NAME, COLUMNS,
                StringUtils.quoteWrapper(user.getEmail()), StringUtils.quoteWrapper(user.getPassword()),
                StringUtils.quoteWrapper(user.getFirstName()), StringUtils.quoteWrapper(user.getLastName()),
                StringUtils.quoteWrapper(user.getPhoneNumber()), user.getLocation().getX(), user.getLocation().getY());
    }

    @Override
    protected String getDeleteStatement(String id) {
        return String.format("delete from %s where %s.%s = %s;", TABLE_NAME, TABLE_NAME, "email", StringUtils.quoteWrapper(id));
    }

    @Override
    protected User convertResultSetToObject(ResultSet rs) throws SQLException {
        return new User(
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("phoneNumber"),
                new Location(
                        rs.getDouble("locationX"),
                        rs.getDouble("locationY")
                ),
                rs.getDouble("credit"),
                rs.getInt("orders")
        );
    }


    @Override
    public List<User> getAll() throws SQLException {
        List<User> result = new ArrayList<User>();
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

    @Override
    public void updateUserCredit(User user) throws SQLException {
        String statement = String.format("update %s set %s = %s, %s = %d where %s = %s;", TABLE_NAME, "credit",
                user.getCredit(), "orders", user.getNumOfOrders(), "email", StringUtils.quoteWrapper(user.getEmail()));
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

}
