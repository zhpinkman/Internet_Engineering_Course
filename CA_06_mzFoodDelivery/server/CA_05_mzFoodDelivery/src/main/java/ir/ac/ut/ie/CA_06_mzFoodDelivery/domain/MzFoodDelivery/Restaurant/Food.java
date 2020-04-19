package ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant;

public class Food {
    private String name;
    private String description;
    private double popularity;
    private double price;
    private String image;

    public Food(String name, String description, double popularity, double price, String image) {
        this.name = name;
        this.description = description;
        this.popularity = popularity;
        this.price = price;
        this.image = image;
    }


    public void print() {
        System.out.println(
                "name: " + name +
                " | description: " + description +
                " | Popularity: " + popularity +
                " | Price: " + price +
                " | Image: " + image
        );
    }


    public String getName() {
        return name;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getImage() { return image; }

    public double getPrice() { return  price; }

    public String getDescription() { return description; }

    public void decreaseFoodAmount(int quantity) {}

    public boolean hasEnoughAmount(int quantity) {
        return true;
    }
}
