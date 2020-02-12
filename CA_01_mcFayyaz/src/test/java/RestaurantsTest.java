import MzFoodDelivery.McZmo;
import MzFoodDelivery.Restaurant.Food;
import MzFoodDelivery.Restaurant.Location;
import MzFoodDelivery.Restaurant.Restaurant;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RestaurantsTest {

    private static McZmo mcZmo = new McZmo();

    @BeforeClass
    public static void beforeRestaurantsTest() {
        try {
            mcZmo.addRestaurant(new Restaurant("a", "a", new Location(0, 1), new ArrayList<>(Arrays.asList(new Food("a", "a", 10, 100), new Food("b", "b", 8, 200)))));
            mcZmo.addRestaurant(new Restaurant("b", "b", new Location(1, 0), new ArrayList<>(Arrays.asList(new Food("a", "a", 10, 100), new Food("b", "b", 10, 200)))));
            mcZmo.addRestaurant(new Restaurant("c", "c", new Location(0, 2), new ArrayList<>(Arrays.asList(new Food("a", "a", 10, 100), new Food("b", "b", 8, 200)))));
            mcZmo.addRestaurant(new Restaurant("d", "d", new Location(0, 2), new ArrayList<>(Arrays.asList(new Food("a", "a", 10, 100), new Food("b", "b", 10, 200)))));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.fail();
        }
    }

    @AfterClass
    public static void afterRestaurantsTest() {
//        System.out.println("after restaurants test");
    }

    @Test
    public void getRecommendedRestaurantsTest() {
        final int RECOMMEND_COUNT = 3;
        List<Restaurant> recommendedRestaurants = mcZmo.getRecommendedRestaurants(RECOMMEND_COUNT);
        StringBuilder output = new StringBuilder();
        for (Restaurant restaurant : recommendedRestaurants) {
            output.append(restaurant.getName());
        }
        Assert.assertEquals("bad", output.toString());
    }

    @Test(expected = Exception.class)
    public void duplicateRestaurantTest() throws Exception{
        mcZmo.addRestaurant(new Restaurant("d", "d", new Location(0, 2), new ArrayList<>(Arrays.asList(new Food("a", "a", 10, 100), new Food("b", "b", 10, 200)))));
    }
}
