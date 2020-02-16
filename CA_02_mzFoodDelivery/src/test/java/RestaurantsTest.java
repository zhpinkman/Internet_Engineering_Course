import HTTPRequestHandler.HTTPRequsestHandler;
import InterfaceServer.InterfaceServer;
import MzFoodDelivery.MzFoodDelivery;
import MzFoodDelivery.Restaurant.Food;
import MzFoodDelivery.Restaurant.Location;
import MzFoodDelivery.Restaurant.Restaurant;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class RestaurantsTest {

    private static InterfaceServer interfaceServer;
    private static MzFoodDelivery mzFoodDelivery;

    public static void addTestRestaurants() {
        try {
            mzFoodDelivery.addRestaurant(new Restaurant("0", "a", "a", new Location(0, 1), new ArrayList<>(Arrays.asList(new Food("a", "a", 10, 100, ""), new Food("b", "b", 8, 200, ""))), ""));
            mzFoodDelivery.addRestaurant(new Restaurant("1", "b", "b", new Location(1, 0), new ArrayList<>(Arrays.asList(new Food("a", "a", 10, 100, ""), new Food("b", "b", 10, 200, ""))), ""));
            mzFoodDelivery.addRestaurant(new Restaurant("2", "c", "c", new Location(0, 2), new ArrayList<>(Arrays.asList(new Food("a", "a", 10, 100, ""), new Food("b", "b", 8, 200, ""))), ""));
            mzFoodDelivery.addRestaurant(new Restaurant("3", "d", "d", new Location(180, 180), new ArrayList<>(Arrays.asList(new Food("a", "a", 10, 100, ""), new Food("b", "b", 10, 200, ""))), ""));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

    }


    @BeforeClass
    public static void beforeRestaurantsTest() {
        final String RESTAURANTS_URI = "http://138.197.181.131:8080/restaurants";
        interfaceServer = new InterfaceServer();
        mzFoodDelivery = interfaceServer.getMzFoodDelivery();
        try {
            addTestRestaurants();
            interfaceServer.start(RESTAURANTS_URI, 8080);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }


    @Test
    public void SuccessfulGetRestaurantTest() {
        String restaurantId = mzFoodDelivery.getNearRestaurants().get(0).getId();
        String url = "http://localhost:8080/restaurants/" + restaurantId;
        String restaurantPageHtml = "";
        try {
            restaurantPageHtml = HTTPRequsestHandler.getRequest(url);
            int statusCode = HTTPRequsestHandler.getStatusCode(url);
            Assert.assertEquals(statusCode, 200);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String restaurantPageHtmlExpected = "";
        try {
            restaurantPageHtmlExpected = interfaceServer.generateGetNearRestaurantPage(restaurantId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(restaurantPageHtml, restaurantPageHtmlExpected);
    }

    @Test
    public void notFoundGetRestaurantTest() {
        String restaurantId = "-1";
        String url = "http://localhost:8080/restaurants/" + restaurantId;
        String restaurantPageHtml = "";
        try {
            restaurantPageHtml = HTTPRequsestHandler.getRequest(url);
            int statusCode = HTTPRequsestHandler.getStatusCode(url);
            Assert.assertEquals(statusCode, 404);
            Assert.assertEquals(restaurantPageHtml, "Not Found");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ForbiddenGetRestaurantTest() {
        String restaurantId = "3";
        String url = "http://localhost:8080/restaurants/" + restaurantId;
        String restaurantPageHtml = "";
        try {
            restaurantPageHtml = HTTPRequsestHandler.getRequest(url);
            int statusCode = HTTPRequsestHandler.getStatusCode(url);
            Assert.assertEquals(statusCode, 403);
            Assert.assertEquals(restaurantPageHtml, "Unauthorized");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @AfterClass
    public static void afterRestaurantsTest() {
    }

}


