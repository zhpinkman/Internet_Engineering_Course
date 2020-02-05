import McFayyaz.McZmo;
import McFayyaz.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import com.google.gson.Gson;

public class TerminalInterface {
    public static void main(String[] args) throws IOException {
        McZmo mcZmo = new McZmo();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = br.readLine()) != null) {
            try {
                String[] input_parts = parseInput(line);
//                if(input_parts.length != 2)
//                    throw new Exception("Error: Bad Format");
                String command = input_parts[0];
                String jsonData = "";
                if (input_parts.length == 2) {
                    jsonData = input_parts[1];
                }
                runCommand(command, jsonData, mcZmo);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    private static String runCommand(String command, String jsonData, McZmo mcZmo){
        try {
            Gson gson = new Gson();
            if (command.equals("addRestaurant")) {
                Restaurant restaurant = gson.fromJson(jsonData, Restaurant.class);
                mcZmo.addRestaurant(restaurant);
                restaurant.print();
            } else if (command.equals("addFood")) {
                Food food = gson.fromJson(jsonData, Food.class);
                Properties properties = gson.fromJson(jsonData, Properties.class);
                String restaurantName = properties.getProperty("restaurantName");
                System.out.println(restaurantName);
                mcZmo.addFood(restaurantName, food);
                food.print();
            } else if (command.equals("getRestaurants")) {
                mcZmo.printRestaurants();
            } else if (command.equals("getRestaurant")) {
                // TODO: 2/5/20  
            } else if (command.equals("getFoods")) {
                // TODO: 2/5/20  
            } else if (command.equals("getFood")) {
                // TODO: 2/5/20
            } else if (command.equals("addToCart")) {
                // TODO: 2/5/20
            } else if (command.equals("getCart")) {
                // TODO: 2/5/20
            } else if (command.equals("finalizeOrder")) {
                // TODO: 2/5/20
            } else if (command.equals("getRecommendedRestaurant")) {
                // TODO: 2/5/20
            } else {
                throw new Exception("Error: Bad Format");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "OK";
    }

    private static String[] parseInput(String input) {
        return input.split(" ", 2);
    }
}
