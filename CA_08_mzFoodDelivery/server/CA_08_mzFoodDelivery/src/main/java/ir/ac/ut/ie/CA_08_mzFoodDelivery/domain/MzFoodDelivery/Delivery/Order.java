package ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.Delivery;



import ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Restaurant;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.User.Cart;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.repository.MzRepository;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.utils.schedulers.BackgroundJobManager;

import java.sql.Date;
import java.sql.SQLException;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Order {

    public Order () {}

    private String userEmail;
    private int id;
    private String statusString;
    private LocalDateTime startingDeliveryTime;
    private String deliveryId;

    public Order(String userEmail, int id, String status, String deliveryId, LocalDateTime localTime) {
        this.userEmail = userEmail;
        this.id = id;
        this.statusString = status;
        this.deliveryId = deliveryId;
        if (localTime != null)
            this.startingDeliveryTime = localTime;
    }

    public Order(String userEmail, int id) {
        this.userEmail = userEmail;
        this.id = id;
        this.statusString = "SEARCHING";
        this.deliveryId = "";
        this.startingDeliveryTime = null;
    }

    public List<OrderItem> getOrderItems() throws SQLException {
        return MzRepository.getInstance().getOrderItems(userEmail, id);
    }

    public Restaurant getOrderRestaurant() throws SQLException {
        List<OrderItem> orderItems = getOrderItems();
        String restaurantId = orderItems.get(0).getRestaurantId();
        return MzRepository.getInstance().findRestaurantById(restaurantId);
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDelivery(Delivery delivery) {
        this.deliveryId = delivery.getId();
        setDeliveringStatus();
        try {
            Restaurant restaurant = getOrderRestaurant();
            double distance = MzFoodDelivery.getInstance().calcDeliveryDistanceToGo(restaurant, delivery);
            double time = distance / delivery.getVelocity();
            startingDeliveryTime = LocalDateTime.now().plusSeconds((long) time);
            MzRepository.getInstance().updateOrder(this);
            BackgroundJobManager.waitForArriving((int) time, this);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public long getRemainingArrivingTime() {
        if (!this.statusString.equals("SEARCHING") && !this.statusString.equals("DELIVERED")) {
            return Duration.between(LocalDateTime.now(), startingDeliveryTime).getSeconds();
        }
        return 0;
    }

    public LocalDateTime getStartingDeliveryTime() {
        return startingDeliveryTime;
    }


    public int getId() {
        return id;
    }

    public void setDeliveringStatus() {
        statusString = String.valueOf(Status.DELIVERING);
    }

    public void setDeliveredStatus() throws SQLException {
        statusString = "DELIVERED";
        MzRepository.getInstance().updateOrder(this);
    }


    public String getStatusString() {
        return statusString;
    }
}
