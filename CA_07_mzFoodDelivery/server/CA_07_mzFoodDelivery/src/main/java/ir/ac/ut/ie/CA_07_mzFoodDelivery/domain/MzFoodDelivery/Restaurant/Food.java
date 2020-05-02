package ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.Restaurant;

import ir.ac.ut.ie.CA_07_mzFoodDelivery.utils.StringUtils;

import java.sql.SQLException;

public class Food {
    protected String restaurantId;
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

    public Food(String restaurantId, String name, String description, double popularity, double price, String image) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.description = description;
        this.popularity = popularity;
        this.price = price;
        this.image = image;
    }


    public void setRestaurantId(String id) {
        this.restaurantId = id;
    }

    public void print() {
//        System.out.println(
//                "name: " + name +
//                " | description: " + description +
//                " | Popularity: " + popularity +
//                " | Price: " + price +
//                " | Image: " + image
//        );
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

    public void decreaseFoodAmount(int quantity) throws SQLException {}

    public boolean hasEnoughAmount(int quantity) {
        return true;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

}
