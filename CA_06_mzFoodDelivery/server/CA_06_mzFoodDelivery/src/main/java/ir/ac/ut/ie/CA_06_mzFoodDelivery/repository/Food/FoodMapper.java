package ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.Food;

import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Food;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.PartyFood;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Restaurant;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.ConnectionPool;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.Mapper;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.utils.CustomPair;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.utils.StringUtils;

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
                            "  restaurantId varchar(255),\n" +
                            "  description text not null,\n" +
                            "  popularity double not null,\n" +
                            "  price double not null,\n" +
                            "  image text not null,\n" +
                            "  count int default -1,\n" +
                            "  newPrice double default -1,\n" +
                            "  primary key(name, restaurantId)\n" +
//                            "  foreign key (restaurantId) references RESTAURANTS(id)\n" +
                            ");",
                    TABLE_NAME));
            st.close();
            con.close();
        }
    }

    public FoodMapper() throws SQLException {
    }

    @Override
    protected String getFindStatement(CustomPair id) {
        return String.format("select * from %s where %s.%s = %s and %s.%s = %s", TABLE_NAME, TABLE_NAME, "restaurantId",
                StringUtils.quoteWrapper(id.getArgs().get(0)), TABLE_NAME, "name", StringUtils.quoteWrapper(id.getArgs().get(1)));
    }

    @Override
    protected String getInsertStatement(Food food) {
        if (food instanceof PartyFood) {
            PartyFood partyFood = (PartyFood) food;
            return String.format("INSERT INTO %s ( %s ) values (%s, %s, %s, %f, %f, %s, %d, %f);", TABLE_NAME, COLUMNS,
                    StringUtils.quoteWrapper(food.getName()), StringUtils.quoteWrapper(food.getRestaurantId()), StringUtils.quoteWrapper(food.getDescription()),
                    food.getPopularity(), ((PartyFood) food).getOldPrice(), StringUtils.quoteWrapper(food.getImage()), partyFood.getCount(), partyFood.getPrice());
        } else {
            return String.format("INSERT INTO %s ( %s ) values (%s, %s, %s, %f, %f, %s, %d, %f);", TABLE_NAME, COLUMNS,
                    StringUtils.quoteWrapper(food.getName()), StringUtils.quoteWrapper(food.getRestaurantId()), StringUtils.quoteWrapper(food.getDescription()),
                    food.getPopularity(), food.getPrice(), StringUtils.quoteWrapper(food.getImage()), -1, -1.0);
        }
    }

    @Override
    protected String getDeleteStatement(CustomPair id) {
        return String.format("delete from %s where %s.%s = %s and %s.%s = %s", TABLE_NAME, TABLE_NAME, "restaurantId",
                StringUtils.quoteWrapper(id.getArgs().get(0)), TABLE_NAME, "name", StringUtils.quoteWrapper(id.getArgs().get(1)));
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
                System.out.println("error in Mapper.deletePartyFoods query.");
                throw ex;
            }
        }
    }

    public List<PartyFood> getPartyFoods() throws SQLException {
        List<PartyFood> result = new ArrayList<PartyFood>();
        String statement = String.format("SELECT * from %s where %s.%s != -1", TABLE_NAME, TABLE_NAME, "newPrice");
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement st = con.prepareStatement(statement);
        ) {
            ResultSet resultSet;
            try {
                resultSet = st.executeQuery();
                while (resultSet.next())
                    result.add((PartyFood) convertResultSetToObject(resultSet));
                return result;
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


    @Override
    public void updateFood(PartyFood food) throws SQLException {
        String statement = String.format("update %s set %s = %d where %s = %s and %s = %s;", TABLE_NAME,
                "count", food.getCount(),
                "restaurantId", StringUtils.quoteWrapper(food.getRestaurantId()),
                "foodName", StringUtils.quoteWrapper(food.getName())
                );
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

    public List<Food> search(String searchPhrase) throws SQLException{
        List<Food> result = new ArrayList<>();
        String searchString = "'%" + searchPhrase + "%'";
        String statement = "SELECT * FROM " + TABLE_NAME +
                " WHERE name LIKE " + searchString +
                " OR description LIKE " + searchString ;

        try {
            Connection con = ConnectionPool.getConnection();
            PreparedStatement st = con.prepareStatement(statement);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next())
                result.add(convertResultSetToObject(resultSet));
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
