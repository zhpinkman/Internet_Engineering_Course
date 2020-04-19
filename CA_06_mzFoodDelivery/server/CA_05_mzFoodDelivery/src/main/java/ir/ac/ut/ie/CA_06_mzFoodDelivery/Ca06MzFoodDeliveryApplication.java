package ir.ac.ut.ie.CA_06_mzFoodDelivery;

import ir.ac.ut.ie.CA_06_mzFoodDelivery.services.RestaurantsService;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.utils.schedulers.ScheduledParty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Ca06MzFoodDeliveryApplication {
	public static void main(String[] args) {
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
	}

}
