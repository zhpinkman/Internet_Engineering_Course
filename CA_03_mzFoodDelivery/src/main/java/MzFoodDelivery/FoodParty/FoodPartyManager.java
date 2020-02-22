package MzFoodDelivery.FoodParty;

import HTTPRequestHandler.HTTPRequestHandler;
import MzFoodDelivery.MzFoodDelivery;
import MzFoodDelivery.Restaurant.Food;
import MzFoodDelivery.Restaurant.Location;
import MzFoodDelivery.Restaurant.PartyFood;
import MzFoodDelivery.Restaurant.Restaurant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class FoodPartyManager {

    private List<PartyFood> partyFoods = new ArrayList<>();

    public void addFood(PartyFood partyFood) {
        partyFoods.add(partyFood);
    }

    public void deleteOldParty(){
        if(partyFoods.size() > 0) {
            for (PartyFood partyFood : partyFoods) {
                partyFood.getRestaurant().deleteFood(partyFood);
            }
            partyFoods.clear();
        }
    }

    public void importFoodPartyFromWeb() throws Exception {
        deleteOldParty();

        String foodPartyJsonString = HTTPRequestHandler.getRequest("http://138.197.181.131:8080/foodparty");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<FoodPartyWebItem> foodPartyWeb = gson.fromJson(foodPartyJsonString, new TypeToken<List<FoodPartyWebItem>>() {
        }.getType());
        for (FoodPartyWebItem item : foodPartyWeb) {
            //System.out.println(item);
            Restaurant restaurant = new Restaurant(item.id, item.name, "", item.location,  new ArrayList<Food>(), item.logo);
            try {
                restaurant = MzFoodDelivery.getInstance().getRestaurant(restaurant.getName());
            }catch (Exception e){
                MzFoodDelivery.getInstance().addRestaurant(restaurant);
            }
            PartyFood partyFood = new PartyFood(item.menu.get(0).name, item.menu.get(0).description,
                    item.menu.get(0).popularity, item.menu.get(0).oldPrice, item.menu.get(0).image,
                    item.menu.get(0).price, item.menu.get(0).count, restaurant);
            MzFoodDelivery.getInstance().addPartyFood(partyFood.getRestaurant().getName(), partyFood);
        }
    }

    public List<PartyFood> getPartyFoods() {
        return partyFoods;
    }
}

class FoodPartyWebItem {
    public String id;
    public String name;
    public Location location;
    public String logo;
    public List<FoodPartyWebFoodItem> menu;
}

class FoodPartyWebFoodItem {
    public int count;
    public double oldPrice;
    public String name;
    public String description;
    public double price;
    public double popularity;
    public String image;
}