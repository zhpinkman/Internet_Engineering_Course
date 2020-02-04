package McFayyaz;

public class Resturant {
    private String name;
    private String description;
    private Location location;
    private Food[] menu;

    public void print() {
        System.out.println("name: " + name + "\n" +
                "description: " + name + "\n" +
                "Location: ");
        location.print();
        for (Food food:menu) {
            food.print();
        }
    }
}
