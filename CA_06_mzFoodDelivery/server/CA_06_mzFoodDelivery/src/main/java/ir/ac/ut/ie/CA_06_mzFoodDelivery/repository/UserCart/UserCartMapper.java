package ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.UserCart;

import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.User.CartItem;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.ConnectionPool;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.Mapper;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.utils.CustomPair;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.utils.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserCartMapper extends Mapper<CartItem, CustomPair> implements IUserCartMapper {

    private static final String COLUMNS = " userEmail, restaurantId, foodName, quantity ";
    private static final String TABLE_NAME = "USERCART";


    public UserCartMapper(Boolean doManage) throws SQLException {
        if (doManage) {
            Connection con = ConnectionPool.getConnection();
            Statement st = con.createStatement();
            st.executeUpdate(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME));
            st.executeUpdate(String.format(
                    "create table %s (\n" +
                            "    userEmail varchar(255),\n" +
                            "    restaurantId varchar(255),\n" +
                            "    foodName varchar(255) not null,\n" +
                            "    quantity int not null default 1,\n" +
                            "    foreign key (foodName, restaurantId) references FOODS(name, restaurantId),\n" +
                            "    foreign key (userEmail) references USERS(email)\n" +
                            "    primary key (userEmail, restaurantId, foodName)\n" +
                            ");",
                    TABLE_NAME));
            st.close();
            con.close();
        }
    }

    public UserCartMapper() throws SQLException {
    }


    @Override
    protected String getFindStatement(CustomPair id) {
        return String.format("select * from %s where %s.%s = %s and %s.%s = %s and %s.%s = %s;",
                TABLE_NAME, TABLE_NAME, "userEmail", StringUtils.quoteWrapper(id.getArgs().get(0)),
                TABLE_NAME, "restaurantId", StringUtils.quoteWrapper(id.getArgs().get(1)),
                TABLE_NAME, "foodName", StringUtils.quoteWrapper(id.getArgs().get(2)));
    }

    @Override
    protected String getInsertStatement(CartItem cartItem) {
        return String.format("insert into %s ( %s ) values (%s, %s, %s, %d);", TABLE_NAME, COLUMNS,
                cartItem.getUserEmail(), cartItem.getRestaurantId(), cartItem.getFoodName(), cartItem.getQuantity());
    }

    @Override
    protected String getDeleteStatement(CustomPair id) {
        return String.format("delete from %s where %s.%s = %s and %s.%s = %s and %s.%s = %s;",
                TABLE_NAME, TABLE_NAME, "userEmail", StringUtils.quoteWrapper(id.getArgs().get(0)),
                TABLE_NAME, "restaurantId", StringUtils.quoteWrapper(id.getArgs().get(1)),
                TABLE_NAME, "foodName", StringUtils.quoteWrapper(id.getArgs().get(2)));
    }

    @Override
    protected CartItem convertResultSetToObject(ResultSet rs) throws SQLException {
        return new CartItem(
                rs.getString("userEmail"),
                rs.getString("restaurantId"),
                rs.getString("foodName"),
                rs.getInt("quantity")
        );
    }

    @Override
    public List<CartItem> getUserCart(String userEmail) throws SQLException {
        List<CartItem> result = new ArrayList<CartItem>();
        String statement = String.format("select * from %s where %s.%s = %s;", TABLE_NAME, TABLE_NAME, "userEmail", StringUtils.quoteWrapper(userEmail));
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
    public void emptyUserCart(String userEmail) throws SQLException {
        String statement = String.format("delete from %s where %s.%s = %s;", TABLE_NAME, TABLE_NAME, "userEmail", StringUtils.quoteWrapper(userEmail));
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement st = con.prepareStatement(statement);
        ) {
            try {
                st.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("error in Mapper.deleteUserCart query.");
                throw ex;
            }
        }
    }

    @Override
    public void updateCartItem(CartItem cartItem) throws SQLException {
        String statement = String.format("update %s set %s = %s where %s = %s and %s = %s and %s;", TABLE_NAME,
                "quantity", cartItem.getQuantity(), "userEmail", StringUtils.quoteWrapper(cartItem.getUserEmail()),
                "restaurantId", StringUtils.quoteWrapper(cartItem.getRestaurantId()),
                "foodName", StringUtils.quoteWrapper(cartItem.getFoodName()));
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
