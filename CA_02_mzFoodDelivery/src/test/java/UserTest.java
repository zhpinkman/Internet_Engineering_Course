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

public class UserTest {

    private static MzFoodDelivery mzFoodDelivery = new MzFoodDelivery();

    @BeforeClass
    public static void beforeUserTest(){
        try {
            mzFoodDelivery.addRestaurant(new Restaurant("a", "a", new Location(0, 1), new ArrayList<>(Arrays.asList(new Food("a", "a", 10, 100), new Food("b", "b", 8, 200)))));
            mzFoodDelivery.addRestaurant(new Restaurant("b", "b", new Location(1, 0), new ArrayList<>(Arrays.asList(new Food("a", "a", 10, 100), new Food("b", "b", 10, 200)))));
            mzFoodDelivery.addRestaurant(new Restaurant("c", "c", new Location(0, 2), new ArrayList<>(Arrays.asList(new Food("a", "a", 10, 100), new Food("b", "b", 8, 200)))));
            mzFoodDelivery.addRestaurant(new Restaurant("d", "d", new Location(0, 2), new ArrayList<>(Arrays.asList(new Food("a", "a", 10, 100), new Food("b", "b", 10, 200)))));
            mzFoodDelivery.addToCart("a", "a");
            mzFoodDelivery.addToCart("a", "b");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void finalizeOrderTest() {
        String output = "";
        output += mzFoodDelivery.getBriefCartJson();
        output += "Order Finalized";
        mzFoodDelivery.finalizeOrder();
        Assert.assertEquals("{\n" +
                "  \"cartItems\": [\n" +
                "    {\n" +
                "      \"quantity\": 1,\n" +
                "      \"foodName\": \"a\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"quantity\": 1,\n" +
                "      \"foodName\": \"b\"\n" +
                "    }\n" +
                "  ]\n" +
                "}" +
                "Order Finalized", output);

        Assert.assertEquals(0, mzFoodDelivery.getUserCartSize());
    }

    @AfterClass
    public static void afterUserTest() {
//        System.out.println("after user tests");
    }
}
