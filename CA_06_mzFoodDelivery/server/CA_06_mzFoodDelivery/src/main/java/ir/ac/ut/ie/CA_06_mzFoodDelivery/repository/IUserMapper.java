package ir.ac.ut.ie.CA_06_mzFoodDelivery.repository;

import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.User.User;

import java.sql.SQLException;
import java.util.List;


// user id assumes to be a String


public interface IUserMapper extends IMapper<User, String> {
    List<User> getAll() throws SQLException;
}