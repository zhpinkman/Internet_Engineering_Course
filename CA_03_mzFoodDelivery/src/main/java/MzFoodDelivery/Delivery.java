package MzFoodDelivery;

import MzFoodDelivery.Restaurant.Location;

public class Delivery {
    private String id;
    private double velocity;
    private Location location;


    public String toString() {
        return id + ", " + String.valueOf(velocity) + String.valueOf(location.getX()) + String.valueOf(location.getY());
    }

}
