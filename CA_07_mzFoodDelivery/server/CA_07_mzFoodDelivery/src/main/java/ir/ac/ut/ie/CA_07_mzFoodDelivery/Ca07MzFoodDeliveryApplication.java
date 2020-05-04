package ir.ac.ut.ie.CA_07_mzFoodDelivery;

import ir.ac.ut.ie.CA_07_mzFoodDelivery.repository.MzRepository;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.services.RestaurantsService;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.utils.schedulers.ScheduledParty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Ca07MzFoodDeliveryApplication {
	public static void main(String[] args) throws SQLException {
//		MzRepository.getInstance().createAllTables();
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		int foodPartyPeriod = 600; //Seconds
		try {
			RestaurantsService.getInstance().importRestaurantsFromWeb();
			// Start Food Party
			new ScheduledParty(foodPartyPeriod).run();
			scheduler.scheduleAtFixedRate(new ScheduledParty(foodPartyPeriod), 0, foodPartyPeriod, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		SpringApplication.run(Ca07MzFoodDeliveryApplication.class, args);

	}

}
