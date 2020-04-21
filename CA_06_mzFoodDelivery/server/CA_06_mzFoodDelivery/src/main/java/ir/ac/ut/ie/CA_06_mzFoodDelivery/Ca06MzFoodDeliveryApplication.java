package ir.ac.ut.ie.CA_06_mzFoodDelivery;

import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Restaurant;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.User.User;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.Restaurant.RestaurantMapper;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.User.UserMapper;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.services.RestaurantsService;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.utils.schedulers.ScheduledParty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Ca06MzFoodDeliveryApplication {
	public static void main(String[] args) throws SQLException {
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		int foodPartyPeriod = 120; //Seconds
		try {
			RestaurantsService.getInstance().importRestaurantsFromWeb();
			// Start Food Party
			new ScheduledParty(foodPartyPeriod).run();
			scheduler.scheduleAtFixedRate(new ScheduledParty(foodPartyPeriod), 0, foodPartyPeriod, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		SpringApplication.run(Ca06MzFoodDeliveryApplication.class, args);


////		testing database
//
//		UserMapper userMapper = new UserMapper(true);
//		userMapper.insert(MzFoodDelivery.getInstance().getUser());
//		List<User> users = userMapper.getAll();
//		for (User user: users) {
//			System.out.println(user.getEmail());
//		}
//		userMapper.delete("ekhamespanah@yahoo.com");

//		RestaurantMapper restaurantMapper = new RestaurantMapper(false);
//		try {
//			Restaurant restaurant = restaurantMapper.find("zhivar");
//		} catch (SQLException ex) {
//			System.out.println("empty");
//		}

	}

}
