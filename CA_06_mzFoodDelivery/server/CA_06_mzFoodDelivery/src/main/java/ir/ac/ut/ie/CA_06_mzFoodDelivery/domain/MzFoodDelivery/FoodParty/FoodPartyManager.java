package ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.FoodParty;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.Food.FoodMapper;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.Restaurant.RestaurantMapper;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.utils.CustomPair;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.utils.HTTPRequestHandler.HTTPRequestHandler;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Food;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.PartyFood;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Restaurant;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodPartyManager {

    private List<PartyFood> partyFoods = new ArrayList<>();

    public void addFood(PartyFood partyFood) {
        partyFoods.add(partyFood);
    }

    public void deleteOldParty() throws SQLException {
        FoodMapper foodMapper = new FoodMapper(false);
        foodMapper.deletePartyFoods();
//        if(partyFoods.size() > 0) {
//            for (PartyFood partyFood : partyFoods) {
//                Restaurant r = partyFood.getRestaurant();
//                if(r != null)
//                    partyFood.getRestaurant().deleteFood(partyFood);
//            }
//            partyFoods.clear();
//        }
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
//            try {
//                restaurant = MzFoodDelivery.getInstance().getRestaurant(restaurant.getName());
//            }catch (Exception e){
//                MzFoodDelivery.getInstance().addRestaurant(restaurant);
//            }
            try {
                restaurant = new RestaurantMapper(false).find(restaurant.getId());

            } catch (SQLException ex) {
                new RestaurantMapper(false).insert(restaurant);
                System.out.println("no restaurant");
            }

//            todo till here i got the food party

            PartyFood partyFood = new PartyFood(item.menu.get(0).name, item.menu.get(0).description,
                    item.menu.get(0).popularity, item.menu.get(0).oldPrice, item.menu.get(0).image,
                    item.menu.get(0).price, item.menu.get(0).count, restaurant.getId());
//            MzFoodDelivery.getInstance().addPartyFood(partyFood.getRestaurant().getName(), partyFood);
            try {
                new FoodMapper(false).delete(new CustomPair(restaurant.getId(), partyFood.getName()));
            } catch (SQLException ex) {
            }
            new FoodMapper(false).insert(partyFood);
        }
    }

    public List<PartyFood> getPartyFoods() {
        return partyFoods;
    }
}



