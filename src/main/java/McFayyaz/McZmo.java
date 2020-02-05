package McFayyaz;

import java.util.ArrayList;
import java.util.List;

public class McZmo {
    List<Restaurant> restaurants;

    public McZmo() {
        restaurants = new ArrayList<Restaurant>();
    }

    public void addResturant(Restaurant restaurant) throws Exception{
        if (restaurants.contains(restaurant))
            throw new Exception("Error: Duplicate restaurant");
        restaurants.add(restaurant);
    }
}
