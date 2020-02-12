package MzFoodDelivery.Restaurant;

public class Food {
    private String name;
    private String description;
    private double popularity;
    private double price;

    public Food(String name, String description, double popularity, double price) {
        this.name = name;
        this.description = description;
        this.popularity = popularity;
        this.price = price;
    }

    public void print() {
        System.out.println("name: " + name + " | description: " + description + " | Popularity: " + popularity + " | Price: " + price);
    }

    public String getName() {
        return name;
    }

    public double getPopularity() {
        return popularity;
    }
}
