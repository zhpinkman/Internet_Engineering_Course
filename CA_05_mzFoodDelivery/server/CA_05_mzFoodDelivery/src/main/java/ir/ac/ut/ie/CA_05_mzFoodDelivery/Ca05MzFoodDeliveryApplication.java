package ir.ac.ut.ie.CA_05_mzFoodDelivery;

import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.services.RestaurantsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ca05MzFoodDeliveryApplication {

	public static void main(String[] args) {
		try {
			RestaurantsService.getInstance().importRestaurantsFromWeb();
			MzFoodDelivery.getInstance().importFoodPartyFromWeb();
		} catch (Exception e) {
			e.printStackTrace();
		}
		SpringApplication.run(Ca05MzFoodDeliveryApplication.class, args);
	}

}
