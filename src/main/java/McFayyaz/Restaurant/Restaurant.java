package McFayyaz.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String description;
    private Location location;
    private List<Food> menu = new ArrayList<>();



    public void print() {
        System.out.println("name: " + name + "\n" +
                "description: " + description + "\n" +
                "Location: ");
        location.print();
        for (Food food:menu) {
            food.print();
        }
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

    private boolean isFoodInMenu(Food food){
        for(Food foodItem: menu){
            if(foodItem.getName().equals(food.getName()))
                return true;
        }
        return false;
    }
    public void addFood(Food food) throws Exception{
        if (isFoodInMenu(food))
            throw new Exception("Error: Duplicate food");
        menu.add(food);
    }

    public int getFoodsPopularityAverage() {
        int foodsPopulationSum = 0;
        for (Food food: menu) {
            foodsPopulationSum += food.getPopularity();
        }
        return foodsPopulationSum / menu.size();
    }


    public double getDistanceFromLocation(Location location) {
        return this.location.getDistanceFromLocation(location);
    }

    public boolean isCopy(Restaurant restaurant) {
        return this.name.equals(restaurant.name);
    }
}
