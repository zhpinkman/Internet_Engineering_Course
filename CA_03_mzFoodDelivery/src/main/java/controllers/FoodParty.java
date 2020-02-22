package controllers;

import HTTPRequestHandler.HTTPRequestHandler;
import MzFoodDelivery.Restaurant.Food;
import MzFoodDelivery.Restaurant.Location;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.List;
import java.util.Properties;

@WebServlet(name = "FoodParty", urlPatterns = "/foodParty")
public class FoodParty extends HttpServlet {
    public void init() throws ServletException {
        try {
            importFoodPartyFromWeb();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void importFoodPartyFromWeb() throws Exception {
        String foodPartyJsonString = HTTPRequestHandler.getRequest("http://138.197.181.131:8080/foodparty");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Properties> foodPartyWeb = gson.fromJson(foodPartyJsonString, new TypeToken<List<Properties>>() {
        }.getType());
        for (Properties property : foodPartyWeb) {
            System.out.println(property.getProperty("Menu"));
//            try {
//                MzFoodDelivery.getInstance().addRestaurant(restaurant);
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
        }
    }
}

class FoodPartyWebItem{
    public String id;
    public String name;
    public Location location;
    public String logo;

}

class FoodPartyWebFoodItem{

}