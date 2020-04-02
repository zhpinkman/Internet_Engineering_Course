package ir.ac.ut.ie.CA_05_mzFoodDelivery.controllers;

import ir.ac.ut.ie.CA_05_mzFoodDelivery.controllers.Exceptions.ExceptionNotFound;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Restaurant;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @GetMapping("")
    public List<Restaurant> getRestaurants() {
        return MzFoodDelivery.getInstance().getNearRestaurants();
    }

    @GetMapping("/{restaurantId}")
    public Restaurant getRestaurantsById(@PathVariable String restaurantId, final HttpServletResponse response) throws IOException {
        try {
            Restaurant restaurant = MzFoodDelivery.getInstance().getRestaurantById(restaurantId);
            return restaurant;
        } catch (Exception e) {
            response.sendError(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return null;
        }
    }
}
