package ir.ac.ut.ie.CA_08_mzFoodDelivery.controllers;

import ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Restaurant;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.utils.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @GetMapping("")
    public List<Restaurant> getRestaurants(
            @RequestParam(defaultValue = Config.DEFAULT_PAGE_NUMBER, value = "pageNumber") int pageNumber,
            @RequestParam(defaultValue = Config.DEFAULT_PAGE_SIZE, value = "pageSize") int pageSize) {

        int limit = pageSize;
        int offset = (pageNumber - 1) * pageSize;
        return MzFoodDelivery.getInstance().getNearRestaurants(limit, offset);
    }

    @GetMapping("/{restaurantId}")
    public Restaurant getRestaurantsById(@PathVariable String restaurantId, final HttpServletResponse response) throws IOException {
        restaurantId = StringUtils.stripTags(restaurantId);
        try {
            Restaurant restaurant = MzFoodDelivery.getInstance().getRestaurantById(restaurantId);
            return restaurant;
        } catch (Exception e) {
            response.sendError(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return null;
        }
    }
}
