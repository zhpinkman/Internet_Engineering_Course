package InterfaceServer;

import HTTPRequestHandler.HTTPRequsestHandler;
import MzFoodDelivery.MzFoodDelivery;
import MzFoodDelivery.Restaurant.Restaurant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.javalin.Javalin;
import java.util.List;

public class InterfaceServer {

    public static void main(String[] args) {
        try {
            MzFoodDelivery mzFoodDelivery = new MzFoodDelivery();

            importRestaurantsFromWeb(mzFoodDelivery);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void importRestaurantsFromWeb(MzFoodDelivery mzFoodDelivery) throws Exception {
        final String RESTAURANTS_URI = "http://138.197.181.131:8080/restaurants";
        String RestaurantsJsonString = HTTPRequsestHandler.getRequest(RESTAURANTS_URI);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Restaurant> restaurants = gson.fromJson(RestaurantsJsonString, new TypeToken<List<Restaurant>>(){}.getType());
        for (Restaurant restaurant: restaurants) {
            mzFoodDelivery.addRestaurant(restaurant);
        }
    }

    public static void runServer(MzFoodDelivery mzFoodDelivery) throws Exception{
        Javalin app = Javalin.create().start(8080);
        app.get("/", ctx -> ctx.result("Hello World"));
    }
}
