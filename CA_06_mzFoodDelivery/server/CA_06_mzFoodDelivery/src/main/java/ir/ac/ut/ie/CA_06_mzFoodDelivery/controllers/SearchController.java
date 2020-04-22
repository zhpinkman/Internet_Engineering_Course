package ir.ac.ut.ie.CA_06_mzFoodDelivery.controllers;

import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Restaurant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @GetMapping("/restaurants")
    public List<Restaurant> searchRestaurants(@RequestParam(required = false, defaultValue = "", value="q") String searchPhrase) {
        return MzFoodDelivery.getInstance().searchRestaurants(searchPhrase);
    }

    @GetMapping("/foods")
    public List<Restaurant> searchFoods(@RequestParam(required = false, defaultValue = "", value="q") String searchPhrase) {
        return MzFoodDelivery.getInstance().searchFoods(searchPhrase);
    }

}
