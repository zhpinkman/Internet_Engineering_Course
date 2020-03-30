package ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.Restaurant;

import java.util.List;

public class Restaurant {
    private String id;
    private String name;
    private String description;
    private Location location;
    private String logo;
    private List<Food> menu;

    private final double MAX_NEAR_DISTANCE = 170;
    private final double averageDeliveryVelocity = 5;
    private final double averageTimeToFindDelivery = 60;

    public Restaurant(String id, String name, String description, Location location, List<Food> menu, String logo) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.description = description;
        this.location = location;
        this.menu = menu;
    }

    public double getAverageTimeToDeliver() {
        return ((location.getDistanceFromLocation(new Location(0, 0)) * 1.5) / averageDeliveryVelocity + averageTimeToFindDelivery);
    }


    public double doublePrecision(double input) {
        return Math.round(input * 100) / 100.0d;
    }


    public void print() {
        System.out.println(
                "id: " + id + "\n" +
                "name: " + name + "\n" +
                "logo: " + logo + "\n" +
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
            if(foodItem.getName().equals(food.getName()) && !(food instanceof PartyFood))
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

    public boolean isNearUser(Location location) {
        return this.location.getDistanceFromLocation(location) < MAX_NEAR_DISTANCE;
    }

    public boolean isCopy(Restaurant restaurant) {
//        return this.name.equals(restaurant.name);
        return this.id.equals(restaurant.id);
    }

    public String getLogo() {
        return this.logo;
    }

    public String getId() {
        return id;
    }


    public String getDescriptionOrDefulatValue(String defaultValue) {
        if (description == null)
            return defaultValue;
        return description;
    }

    public String getPropertyOrDefaultValue(String propertyName, String defaultValue) {
        String fieldValue = null;
        try {
            fieldValue = (String) getClass().getDeclaredField(propertyName).get(this);
        }
        catch (NoSuchFieldException | IllegalAccessException exception)
        {
//            System.out.println(exception.getMessage());
        }
        System.out.println(fieldValue);
        if (fieldValue != null)
            return fieldValue;
        return defaultValue;
    }

    public void deleteFood(PartyFood partyFood) {
        menu.remove(partyFood);
    }
}
