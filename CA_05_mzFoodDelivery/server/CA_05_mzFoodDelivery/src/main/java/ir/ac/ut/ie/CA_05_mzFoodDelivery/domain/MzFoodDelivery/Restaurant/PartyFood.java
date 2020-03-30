package ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.Restaurant;

public class PartyFood extends Food {
    private Restaurant restaurant;
    private int count;
    private double newPrice;

    public PartyFood(String name, String description, double popularity, double price, String image, double newPrice, int count, Restaurant restaurant) {
        super(name, description, popularity, price, image);
        this.count = count;
        this.newPrice = newPrice;
        this.restaurant = restaurant;
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public int getCount() {
        return count;
    }
}
