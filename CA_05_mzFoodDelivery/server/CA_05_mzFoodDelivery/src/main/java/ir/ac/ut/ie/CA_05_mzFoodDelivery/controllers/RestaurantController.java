package ir.ac.ut.ie.CA_05_mzFoodDelivery.controllers;

import ir.ac.ut.ie.CA_05_mzFoodDelivery.controllers.Exceptions.ExceptionNotFound;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Restaurant;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    @RequestMapping(
            value = "/restaurants",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Restaurant> restaurants() {
        return MzFoodDelivery.getInstance().getNearRestaurants();
    }

    @RequestMapping(
            value = "/restaurants",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            params = "id"
    )
    public Restaurant restaurants(@RequestParam(name = "id", required = false) String restaurantId) {
        try {
            Restaurant restaurant = MzFoodDelivery.getInstance().getRestaurantById(restaurantId);
            return restaurant;
        } catch (Exception e) {
            return null;
        }

    }
}
