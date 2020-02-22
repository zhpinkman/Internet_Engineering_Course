package MzFoodDelivery;

import MzFoodDelivery.User.Cart;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Order {


    private static double max_id = 0;

    private double id;
    private Cart cart;
    private Status status;
    private Delivery delivery;
    private LocalTime startingDeliveryTime;

    public Order(Cart cart) {
        this.id = max_id ++;
        this.status = Status.SEARCHING;
        this.cart = cart;
    }


    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        setDeliveringStatus();
        startingDeliveryTime = LocalTime.now();
    }

    public long getRemainingArrivingTime() {
        return startingDeliveryTime.until(LocalTime.now(), ChronoUnit.SECONDS);
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public Cart getCart() {
        return cart;
    }

    public double getId() {
        return id;
    }

    public void setDeliveringStatus() {
        status = Status.DELIVERING;
    }

    public void setDeliveredStatus() {
        status = Status.DELIVERED;
    }

    public void setSearchingStatus() {
        status = Status.SEARCHING;
    }


    public Status getStatus() {
        return status;
    }
}
