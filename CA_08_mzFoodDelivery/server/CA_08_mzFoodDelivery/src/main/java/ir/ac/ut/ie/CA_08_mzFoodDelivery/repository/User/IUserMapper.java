package ir.ac.ut.ie.CA_08_mzFoodDelivery.repository.User;

import ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.User.User;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.repository.IMapper;

import java.sql.SQLException;
import java.util.List;


// user id assumes to be a String


public interface IUserMapper extends IMapper<User, String> {
    List<User> getAll() throws SQLException;
    void updateUserCredit(User user) throws SQLException;
}
