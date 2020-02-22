package MzFoodDelivery;

import MzFoodDelivery.User.Cart;

public class Order {


    private static double max_id = 0;

    private double id;
    private Cart cart;
    private Status status;

    public Order(Cart cart) {
        this.id = max_id ++;
        this.status = Status.SEARCHING;
        this.cart = cart;
    }

    public void setArrivingStatus() {
        status = Status.ARRIVING;
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
