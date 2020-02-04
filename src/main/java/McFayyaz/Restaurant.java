package McFayyaz;

public class Restaurant {
    private String name;
    private String description;
    private Location location;
    private Food[] menu;

    public void print() {
        System.out.println("name: " + name + "\n" +
                "description: " + description + "\n" +
                "Location: ");
        location.print();
        for (Food food:menu) {
            food.print();
        }
    }

    public void testRestaurant(){

    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Location getLocation() {
        return location;
    }

    public Food[] getMenu() {
        return menu;
    }
}
