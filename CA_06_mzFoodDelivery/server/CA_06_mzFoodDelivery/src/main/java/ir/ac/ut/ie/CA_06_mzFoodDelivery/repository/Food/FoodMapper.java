package ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.Food;

import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Food;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.PartyFood;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.User.User;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.ConnectionPool;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.Mapper;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.utils.CustomPair;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.utils.StringUtils;

import javax.swing.plaf.IconUIResource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class FoodMapper extends Mapper<Food, CustomPair> implements IFoodMapper {

    private static final String COLUMNS = " name, restaurantId, description, popularity, price, image, count, newPrice ";
    private static final String TABLE_NAME = "FOODS";

    public FoodMapper(Boolean doManage) throws SQLException {
        if (doManage) {
            Connection con = ConnectionPool.getConnection();
            Statement st = con.createStatement();
            st.executeUpdate(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME));
            st.executeUpdate(String.format(
                    "create table FOODS (\n" +
                            "  name varchar(255),\n" +
                            "  restaurantId varchar(255) references RESTAURANTS(id),\n" +
                            "  description text not null,\n" +
                            "  popularity double not null,\n" +
                            "  price double not null,\n" +
                            "  image text not null,\n" +
                            "  count int not null default -1,\n" +
                            "  newPrice double,\n" +
                            "  primary key(name, restaurantId)\n" +
                            ");",
                    TABLE_NAME));
            st.close();
            con.close();
        }
    }

    @Override
    protected String getFindStatement(CustomPair id) {
        return String.format("select * from %s where %s.%s = %s and %s.%s = %s", TABLE_NAME, TABLE_NAME, "restaurantId",
                StringUtils.quoteWrapper(id.getFirst()), TABLE_NAME, "name", StringUtils.quoteWrapper(id.getSecond()));
    }

    @Override
    protected String getInsertStatement(Food food) {
        return String.format("insert into %s ( %s ) values (%s, %s, %s, %f, %f, %s, %d, %f);", TABLE_NAME, COLUMNS,
                StringUtils.quoteWrapper(food.getName()), StringUtils.quoteWrapper(food.getRestaurantId()), StringUtils.quoteWrapper(food.getDescription()),
                food.getPopularity(), food.getPrice(), StringUtils.quoteWrapper(food.getImage()), food.getCount(), food.getNewPrice());
    }

    @Override
    protected String getDeleteStatement(CustomPair id) {
        return String.format("delete from %s where %s.%s = %s and %s.%s = %s", TABLE_NAME, TABLE_NAME, "restaurantId",
                StringUtils.quoteWrapper(id.getFirst()), TABLE_NAME, "name", StringUtils.quoteWrapper(id.getSecond()));
    }

    @Override
    protected Food convertResultSetToObject(ResultSet rs) throws SQLException {
        if (rs.getInt("count") == -1) {
            return new Food(rs.getString("restaurantId"), rs.getString("name"), rs.getString("description"),
                    rs.getDouble("popularity"), rs.getDouble("price"), rs.getString("image"));
        } else {
            return new PartyFood(rs.getString("name"), rs.getString("description"), rs.getDouble("popularity"),
                    rs.getDouble("price"), rs.getString("image"), rs.getDouble("newPrice"), rs.getInt("count"), rs.getString("restaurantId"));
        }
    }

    @Override
    public void deletePartyFoods() throws SQLException {
        String statement = String.format("delete from %s where %s.%s != -1", TABLE_NAME, TABLE_NAME, "newPrice");
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement st = con.prepareStatement(statement);
        ) {
            try {
                st.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("error in Mapper.findAll query.");
                throw ex;
            }
        }
    }

    @Override
    public List<Food> getRestaurantMenu(String restaurantId) throws SQLException {
        List<Food> result = new ArrayList<Food>();
        String statement = String.format("select * from %s where %s.%s = %s;", TABLE_NAME, TABLE_NAME, "restaurantId", StringUtils.quoteWrapper(restaurantId));
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
}
