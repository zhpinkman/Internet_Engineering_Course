package MzFoodDelivery.Restaurant;

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
    public void decreaseFoodAmount() {
        count --;
    }

    @Override
    public boolean hasEnoughAmount(int quantity) {
        if (count < quantity)
            return false;
        return true;
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
