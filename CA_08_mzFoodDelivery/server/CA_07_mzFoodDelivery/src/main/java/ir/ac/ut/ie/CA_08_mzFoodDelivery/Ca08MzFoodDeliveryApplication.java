package ir.ac.ut.ie.CA_08_mzFoodDelivery;

import ir.ac.ut.ie.CA_08_mzFoodDelivery.services.RestaurantsService;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.utils.schedulers.ScheduledParty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Ca08MzFoodDeliveryApplication {
	public static void main(String[] args) throws SQLException {
//		MzRepository.getInstance().createAllTables();
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		int foodPartyPeriod = 30; //Seconds
		try {
			RestaurantsService.getInstance().importRestaurantsFromWeb();
			// Start Food Party
			new ScheduledParty(foodPartyPeriod).run();
			scheduler.scheduleAtFixedRate(new ScheduledParty(foodPartyPeriod), 0, foodPartyPeriod, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		SpringApplication.run(Ca08MzFoodDeliveryApplication.class, args);

	}

}
