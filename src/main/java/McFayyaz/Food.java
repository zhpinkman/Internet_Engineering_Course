package McFayyaz;

public class Food {
    private String name;
    private String description;
    private double popularity;
    private double price;

    public void print() {
        System.out.println("name: " + name + " | description: " + description + " | Popularity: " + popularity + " | Price: " + price);
    }
}
