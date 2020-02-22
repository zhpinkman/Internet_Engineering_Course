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
    public double getPrice() {
        return newPrice;
    }

    public double getOldPrice() {
        return super.getPrice();
    }

}
