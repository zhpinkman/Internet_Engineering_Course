package ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Delivery;



import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.User.Cart;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.utils.schedulers.BackgroundJobManager;

import java.sql.Date;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class Order {

    public Order () {}


    private int max_id = 0;

    private String userEmail;
    private int id;
    private Cart cart;
    private Status status;
    private String statusString;
    private Delivery delivery;
    private LocalTime startingDeliveryTime;
    private String deliveryId;
    private List<OrderItem> orderItems;

    public Order(String userEmail, int id, String status, String deliveryId, Date localTime) {
        this.userEmail = userEmail;
        this.id = id;
        this.statusString = status;
        this.deliveryId = deliveryId;
    }

    public Order(String userEmail, int id) {
        this.userEmail = userEmail;
        this.id = id;
        this.statusString = "SEARCHING";
        this.deliveryId = "";
        this.startingDeliveryTime = null;
    }


    public String getUserEmail() {
        return userEmail;
    }

    public Order(Cart cart) {
        this.id = max_id ++;
        this.status = Status.SEARCHING;
        this.cart = cart;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        setDeliveringStatus();
        try {
            double distance = MzFoodDelivery.getInstance().calcDeliveryDistanceToGo(cart.getRestaurant(), delivery);
            double time = distance / delivery.getVelocity();
            startingDeliveryTime = LocalTime.now().plusSeconds((long) time);
            BackgroundJobManager.waitForArriving((int) time, this);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public long getRemainingArrivingTime() {
        if (this.status != Status.SEARCHING) {
            return Duration.between(LocalTime.now(), startingDeliveryTime).getSeconds();
        }
        return 0;
    }

    public LocalTime getStartingDeliveryTime() {
        return startingDeliveryTime;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public Cart getCart() {
        return cart;
    }

    public int getId() {
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
