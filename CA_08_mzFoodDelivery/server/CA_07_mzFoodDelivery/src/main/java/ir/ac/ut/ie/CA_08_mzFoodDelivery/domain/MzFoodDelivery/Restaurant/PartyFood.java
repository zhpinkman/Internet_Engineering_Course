package ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.Restaurant;

import ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.repository.MzRepository;

import java.beans.Transient;
import java.sql.SQLException;

public class PartyFood extends Food {
    private double newPrice;
    private int count;

    public PartyFood(String name, String description, double popularity, double price, String image, double newPrice, int count, String restaurantId) {
        super(restaurantId, name, description, popularity, price, image);
        this.count = count;
        this.newPrice = newPrice;
    }

    @Override
    public void decreaseFoodAmount(int quantity) throws SQLException {
        count -= quantity;
        System.out.println(count);
        MzRepository.getInstance().updatePartyFood(this);
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


    public void increaseFoodAmount() throws SQLException {
        this.count += 1;
        MzRepository.getInstance().updatePartyFood(this);
    }
}
