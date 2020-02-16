import InterfaceServer.InterfaceServer;
import MzFoodDelivery.MzFoodDelivery;
import MzFoodDelivery.Restaurant.Food;
import MzFoodDelivery.Restaurant.Location;
import MzFoodDelivery.Restaurant.Restaurant;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class UserTest {

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
    public static void beforeUserTests() {
        final String RESTAURANTS_URI = "http://138.197.181.131:8080/restaurants";
        interfaceServer = new InterfaceServer();
        mzFoodDelivery = interfaceServer.getMzFoodDelivery();
        addTestRestaurants();
        try {
            interfaceServer.start(RESTAURANTS_URI, 8080);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Test
    public void notEnoughCreditFinalizeOrderTest() {
        try {
            mzFoodDelivery.addToCart("a", "a");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        HttpResponse<String> response = Unirest.post("http://localhost:8080/finalize").asString();
        Assert.assertEquals("credit is not enough for finalizing your order", response.getBody());
        Assert.assertEquals(response.getStatus(), 400);
        mzFoodDelivery.getCart().emptyCart();
    }

    @Test
    public void emptyCartFinalizingOrderTest() {
        HttpResponse<String> response = Unirest.post("http://localhost:8080/finalize").asString();
        Assert.assertEquals("user cart is empty", response.getBody());
        Assert.assertEquals(response.getStatus(), 400);
    }

    @Test
    public void successfulFinalizingOrderTest() {
        try {
            mzFoodDelivery.addToCart("a", "a");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        mzFoodDelivery.chargeUserCredit(200);
        HttpResponse<String> response = Unirest.post("http://localhost:8080/finalize").asString();
        Assert.assertEquals(response.getStatus(), 200);
        Assert.assertEquals(mzFoodDelivery.getUser().getCredit(), 100, 0);
        Assert.assertEquals(mzFoodDelivery.getUserCartSize(), 0);
    }





    @AfterClass
    public static void afterUserTests() {
        interfaceServer.stop();
    }
}
