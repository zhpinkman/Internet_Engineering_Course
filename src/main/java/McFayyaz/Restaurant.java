package McFayyaz;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String description;
    private Location location;
    private List<Food> menu = new ArrayList<Food>();



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

    public Food getFood(String foodName) throws Exception {
        for (Food food: menu) {
            if (food.getName().equals(foodName))
                return food;
        }
        throw new Exception("Error: food does not exists");
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

    public List<Food> getMenu() {
        return menu;
    }

    public void addFood(Food food) throws Exception{
        if (menu.contains(food))
            throw new Exception("Error: Duplicate food");
        menu.add(food);
    }
}
