import McFayyaz.McZmo;
import McFayyaz.Restaurant.Food;
import McFayyaz.Restaurant.Restaurant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.List;
import java.util.Properties;

class Interface {
    public static void main(String[] args){
        PrintStream consoleOut = System.out;
        InputStream consoleIn = System.in;
        start(consoleIn, consoleOut);
    }

    public static void start(InputStream inputStream, PrintStream outputStream) {
        System.setOut(outputStream);
        McZmo mcZmo = new McZmo();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            while ((line = br.readLine()) != null) {
                try {
                    String[] input_parts = parseInput(line);
//                    if (input_parts.length != 2)
//                        throw new Exception("Error: Bad Format");
                    String command = input_parts[0];
                    String jsonData = "";
                    if (input_parts.length == 2) {
                        jsonData = input_parts[1];
                    }
                    runCommand(command, jsonData, mcZmo);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void runCommand(String command, String jsonData, McZmo mcZmo) {
        final int RECOMMEND_COUNT = 3;
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            switch (command) {
                case "addRestaurant": {
                    System.out.println("Adding Restaurant");
                    Restaurant restaurant = gson.fromJson(jsonData, Restaurant.class);
                    mcZmo.addRestaurant(restaurant);
                    restaurant.print();
                    break;
                }
                case "addFood": {
                    System.out.println("Adding Food");
                    Food food = gson.fromJson(jsonData, Food.class);
                    Properties properties = gson.fromJson(jsonData, Properties.class);
                    String restaurantName = properties.getProperty("restaurantName");
//                System.out.println(restaurantName);
                    mcZmo.addFood(restaurantName, food);
                    food.print();

                    break;
                }
                case "getRestaurants":
                    System.out.println("Getting Restaurants");
                    mcZmo.printRestaurants();

                    break;
                case "getRestaurant": {
                    System.out.println("Getting Restaurant");
                    Properties properties = gson.fromJson(jsonData, Properties.class);
                    String restaurantName = properties.getProperty("name");
                    Restaurant restaurant = mcZmo.getRestaurant(restaurantName);
                    String restaurantDetail = gson.toJson(restaurant);
                    System.out.println(restaurantDetail);

                    break;
                }
                case "getFood": {
                    System.out.println("Getting Food");
                    Properties properties = gson.fromJson(jsonData, Properties.class);
                    String restaurantName = properties.getProperty("restaurantName");
                    String foodName = properties.getProperty("foodName");
                    Food food = mcZmo.getFood(restaurantName, foodName);
                    String foodDetail = gson.toJson(food);
                    System.out.println(foodDetail);

                    break;
                }
                case "addToCart": {
                    System.out.println("Adding to Cart");
                    Properties properties = gson.fromJson(jsonData, Properties.class);
                    String restaurantName = properties.getProperty("restaurantName");
                    String foodName = properties.getProperty("foodName");
                    mcZmo.addToCart(restaurantName, foodName);

                    break;
                }
                case "getCart":
                    System.out.println("Getting cart");
                    System.out.println(mcZmo.getBriefCartJson());

                    break;
                case "finalizeOrder":
                    System.out.println("Finalizing Order");
                    System.out.println(mcZmo.getBriefCartJson());
                    mcZmo.finalizeOrder();
                    System.out.println("Order Finalized");

                    break;
                case "getRecommendedRestaurants":
                    System.out.println("Getting Recommended Restaurant");
                    printRecommendedRestaurants(mcZmo.getRecommendedRestaurants(RECOMMEND_COUNT));

                    break;
                default:
                    throw new Exception("Error: Bad Format");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printRecommendedRestaurants(List<Restaurant> firstThreeRestaurants) {
        for (Restaurant restaurant : firstThreeRestaurants) {
            System.out.println(restaurant.getName());
        }
    }

    private static String[] parseInput(String input) {
        return input.split(" ", 2);
    }
}
