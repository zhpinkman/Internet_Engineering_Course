package ir.ac.ut.ie.CA_05_mzFoodDelivery.controllers;

import ir.ac.ut.ie.CA_05_mzFoodDelivery.controllers.Exceptions.ExceptionNotFound;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Restaurant;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @GetMapping("")
    public List<Restaurant> getRestaurants() {
        return MzFoodDelivery.getInstance().getNearRestaurants();
    }

    @GetMapping("/{restaurantId}")
    public Restaurant getRestaurantsById(@PathVariable String restaurantId) {
        try {
            Restaurant restaurant = MzFoodDelivery.getInstance().getRestaurantById(restaurantId);
            return restaurant;
        } catch (Exception e) {
            return null;
        }
    }
}
