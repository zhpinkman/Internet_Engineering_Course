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
		try {
			RestaurantsService.getInstance().importRestaurantsFromWeb();
			MzFoodDelivery.getInstance().importFoodPartyFromWeb();
			new ScheduledParty().run();
			scheduler.scheduleAtFixedRate(new ScheduledParty(), 15, 15, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		SpringApplication.run(Ca05MzFoodDeliveryApplication.class, args);
	}

}
