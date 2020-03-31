package ir.ac.ut.ie.CA_05_mzFoodDelivery.controllers;

import com.google.gson.Gson;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Restaurant;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.services.RestaurantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @GetMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Restaurant> restaurants() {
        List<Restaurant> r = MzFoodDelivery.getInstance().getNearRestaurants();
        System.out.println(r);
        return r;
    }
}
