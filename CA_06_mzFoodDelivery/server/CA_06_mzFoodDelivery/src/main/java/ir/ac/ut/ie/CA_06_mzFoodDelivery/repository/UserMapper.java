package ir.ac.ut.ie.CA_06_mzFoodDelivery.repository;


import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.User.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserMapper extends Mapper<User, String> implements IUserMapper {

//    private static final String COLUMNS = " id, text ";
    private static final String TABLE_NAME = "USERS";

    private Boolean doManage;

    public UserMapper(Boolean doManage) throws SQLException {
//        if (this.doManage = doManage) {
//            todo create user table
//            Connection con = ConnectionPool.getConnection();
//            Statement st = con.createStatement();
//            st.executeUpdate(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME));
//            st.executeUpdate(String.format(
//                    "CREATE TABLE  %s " +
//                            "(" +
//                            "id integer PRIMARY KEY, " +
//                            "text TEXT" +
//                            ");",
//                    TABLE_NAME));
//            st.close();
//            con.close();
//        }
    }



    @Override
    protected String getFindStatement(String id) {
        return null;
    }

    @Override
    protected String getInsertStatement(User user) {
        return null;
    }

    @Override
    protected String getDeleteStatement(String id) {
        return null;
    }

    @Override
    protected User convertResultSetToObject(ResultSet rs) throws SQLException {
        return null;
    }


    @Override
    public List<User> getAll() throws SQLException {
        return null;
    }

}
