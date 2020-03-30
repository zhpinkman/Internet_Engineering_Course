package restControllers;

import HTTPRequestHandler.HTTPRequestHandler;
import MzFoodDelivery.MzFoodDelivery;
import MzFoodDelivery.Restaurant.Restaurant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.List;

@RestController
public class RestaurantsController {
    public RestaurantsController() {
        try {
            importRestaurantsFromWeb();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(
            value = "/restaurants",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Restaurant> restaurants() {
        return MzFoodDelivery.getInstance().getNearRestaurants();
    }




    public void importRestaurantsFromWeb() throws Exception {
        String RestaurantsJsonString = HTTPRequestHandler.getRequest("http://138.197.181.131:8080/restaurants");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Restaurant> restaurants = gson.fromJson(RestaurantsJsonString, new TypeToken<List<Restaurant>>() {
        }.getType());
        for (Restaurant restaurant : restaurants) {
//            System.out.println(counter + "----------------");
//            restaurant.print();
            try {
                MzFoodDelivery.getInstance().addRestaurant(restaurant);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
