import McFayyaz.McZmo;
import McFayyaz.Restaurant.Food;
import McFayyaz.Restaurant.Location;
import McFayyaz.Restaurant.Restaurant;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class UserTest {

    private static McZmo mcZmo = new McZmo();

    @BeforeClass
    public static void beforeUserTest(){
        try {
            mcZmo.addRestaurant(new Restaurant("a", "a", new Location(0, 1), new ArrayList<>(Arrays.asList(new Food("a", "a", 10, 100), new Food("b", "b", 8, 200)))));
            mcZmo.addRestaurant(new Restaurant("b", "b", new Location(1, 0), new ArrayList<>(Arrays.asList(new Food("a", "a", 10, 100), new Food("b", "b", 10, 200)))));
            mcZmo.addRestaurant(new Restaurant("c", "c", new Location(0, 2), new ArrayList<>(Arrays.asList(new Food("a", "a", 10, 100), new Food("b", "b", 8, 200)))));
            mcZmo.addRestaurant(new Restaurant("d", "d", new Location(0, 2), new ArrayList<>(Arrays.asList(new Food("a", "a", 10, 100), new Food("b", "b", 10, 200)))));
            mcZmo.addToCart("a", "a");
            mcZmo.addToCart("a", "b");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void finalizeOrderTest() {
        String output = "";
        output += mcZmo.getBriefCartJson();
        output += "Order Finalized";
        mcZmo.finalizeOrder();
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

        Assert.assertEquals(0, mcZmo.getUserCartSize());
    }

    @AfterClass
    public static void afterUserTest() {
//        System.out.println("after user tests");
    }
}
