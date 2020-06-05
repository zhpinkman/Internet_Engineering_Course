package ir.ac.ut.ie.CA_08_mzFoodDelivery.controllers;

import ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Restaurant;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.utils.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @GetMapping("/restaurants")
    public List<Restaurant> searchRestaurants(
            @RequestParam(required = true, value = "q") String searchPhrase,
            @RequestParam(defaultValue = Config.DEFAULT_PAGE_NUMBER, value = "pageNumber") int pageNumber,
            @RequestParam(defaultValue = Config.DEFAULT_PAGE_SIZE, value = "pageSize") int pageSize) {

        int limit = pageSize;
        int offset = (pageNumber - 1) * pageSize;
        searchPhrase = StringUtils.stripTags(searchPhrase);
        return MzFoodDelivery.getInstance().searchRestaurants(searchPhrase, limit, offset);
    }

    @GetMapping("/foods")
    public List<Restaurant> searchFoods(
            @RequestParam(required = true, value = "q") String searchPhrase,
            @RequestParam(defaultValue = Config.DEFAULT_PAGE_NUMBER, value = "pageNumber") int pageNumber,
            @RequestParam(defaultValue = Config.DEFAULT_PAGE_SIZE, value = "pageSize") int pageSize) {

        int limit = pageSize;
        int offset = (pageNumber - 1) * pageSize;
        searchPhrase = StringUtils.stripTags(searchPhrase);
        return MzFoodDelivery.getInstance().searchFoods(searchPhrase, limit, offset);
    }

}
