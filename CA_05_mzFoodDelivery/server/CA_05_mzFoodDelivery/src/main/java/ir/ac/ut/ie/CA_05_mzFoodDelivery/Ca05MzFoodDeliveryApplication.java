package ir.ac.ut.ie.CA_05_mzFoodDelivery;

import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.services.RestaurantsService;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.utils.schedulers.ScheduledParty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Ca05MzFoodDeliveryApplication {
	public static void main(String[] args) {
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		int foodPartyPeriod = 15; //Seconds
		try {
			RestaurantsService.getInstance().importRestaurantsFromWeb();

			// Start Food Party
			new ScheduledParty(foodPartyPeriod).run();
			scheduler.scheduleAtFixedRate(new ScheduledParty(foodPartyPeriod), 0, foodPartyPeriod, TimeUnit.SECONDS);

		} catch (Exception e) {
			e.printStackTrace();
		}
		SpringApplication.run(Ca05MzFoodDeliveryApplication.class, args);
	}

}
