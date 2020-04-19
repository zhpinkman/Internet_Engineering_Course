package ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant;

import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;

import java.beans.Transient;

public class PartyFood extends Food {
    private String restaurantId;
    private int count;
    private double newPrice;

    public PartyFood(String name, String description, double popularity, double price, String image, double newPrice, int count, String restaurantId) {
        super(name, description, popularity, price, image);
        this.count = count;
        this.newPrice = newPrice;
        this.restaurantId = restaurantId;
    }

    @Override
    public void decreaseFoodAmount(int quantity) {
        count -= quantity;
    }

    @Override
    public boolean hasEnoughAmount(int quantity) {
        return count >= quantity;
    }

    @Override
    public double getPrice() {
        return newPrice;
    }

    public double getOldPrice() {
        return super.getPrice();
    }

    @Transient
    public Restaurant getRestaurant() {
        try {
            return MzFoodDelivery.getInstance().getRestaurantById(this.restaurantId);
        }catch (Exception e){
            return null;
        }
    }

    public String getRestaurantName() {
        return this.getRestaurant().getName();
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurantId = restaurant.getId();
    }

    public int getCount() {
        return count;
    }

    public String getRestaurantId() { return restaurantId; }

    public void increaseFoodAmount() {
        this.count += 1;
    }
}
