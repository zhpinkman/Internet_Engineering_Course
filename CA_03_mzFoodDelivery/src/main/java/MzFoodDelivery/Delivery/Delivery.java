package MzFoodDelivery.Delivery;

import MzFoodDelivery.MzFoodDelivery;
import MzFoodDelivery.Restaurant.Location;

public class Delivery {
    private String id;
    private double velocity;
    private Location location;

    public Location getLocation() {
        return location;
    }

    public double getVelocity() {
        return velocity;
    }

    public String toString() {
        return id + ", " + String.valueOf(velocity) + String.valueOf(location.getX()) + String.valueOf(location.getY());
    }

}
