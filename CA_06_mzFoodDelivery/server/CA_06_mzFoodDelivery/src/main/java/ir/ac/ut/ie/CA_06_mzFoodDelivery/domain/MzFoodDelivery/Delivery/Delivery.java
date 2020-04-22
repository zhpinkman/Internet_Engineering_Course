package ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Delivery;


import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Location;

public class Delivery {
    private String id;
    private double velocity;
    private Location location;

    public String getId() {
        return id;
    }

    public Delivery(String id, double velocity, Location location) {
        this.id = id;
        this.velocity = velocity;
        this.location = location;
    }

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
