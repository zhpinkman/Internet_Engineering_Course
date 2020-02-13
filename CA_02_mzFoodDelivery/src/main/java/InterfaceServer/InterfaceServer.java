package InterfaceServer;

import HTTPRequestHandler.HTTPRequsestHandler;
import MzFoodDelivery.MzFoodDelivery;
import MzFoodDelivery.Restaurant.Location;
import MzFoodDelivery.Restaurant.Restaurant;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.javalin.Javalin;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class InterfaceServer {

    private MzFoodDelivery mzFoodDelivery = new MzFoodDelivery();

    public void start(final String RESTAURANTS_URI, final int port) {
        try {
            System.out.println("Importing Restaurants...");
            importRestaurantsFromWeb(RESTAURANTS_URI);
            runServer(port);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void runServer(final int port) throws Exception {
        Javalin app = Javalin.create().start(port);
        app.get("/", ctx -> ctx.result("Hello World"));
        app.get("/hello/:name", ctx -> {
            ctx.result("Hello: " + ctx.pathParam("name"));
        });

        app.get("restaurant/near/", ctx -> {
            try {
                ctx.html(generateGetNearRestaurantPage());
            }catch (Exception e){
                System.out.println(e.getMessage());
                ctx.status(502);
            }
        });

    }

    public String generateGetNearRestaurantPage() throws Exception{
        String nearRestaurantsHTML = readResourceFile("restaurantsBefore.html");
        List<Restaurant> nearRestaurants = mzFoodDelivery.getNearRestaurants();
        String restaurantItemHTML = readResourceFile("restaurantsItem.html");
        int counter = 1;
        for(Restaurant restaurant: nearRestaurants){
            HashMap<String, String> context = new HashMap<>();
            context.put("id", restaurant.getId());
            context.put("logo", restaurant.getLogo());
            context.put("name", restaurant.getName());
            context.put("distance", Double.toString(restaurant.getDistanceFromLocation(new Location(0,0))));
//            context.put("description", restaurant.getDescription());
            nearRestaurantsHTML += HTMLHandler.fillTemplate(restaurantItemHTML, context);
        }
        nearRestaurantsHTML += readResourceFile("restaurantsAfter.html");
        return nearRestaurantsHTML;
    }

    private String readResourceFile(String fileName) throws Exception{
        File file = new File(Resources.getResource("templates/" + fileName).toURI());
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }

    public void importRestaurantsFromWeb(String uri) throws Exception {
        String RestaurantsJsonString = HTTPRequsestHandler.getRequest(uri);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Restaurant> restaurants = gson.fromJson(RestaurantsJsonString, new TypeToken<List<Restaurant>>() {
        }.getType());
        int counter = 1;
        for (Restaurant restaurant : restaurants) {
            System.out.println(counter + "----------------");
            counter++;
            restaurant.print();
            try {
                mzFoodDelivery.addRestaurant(restaurant);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
