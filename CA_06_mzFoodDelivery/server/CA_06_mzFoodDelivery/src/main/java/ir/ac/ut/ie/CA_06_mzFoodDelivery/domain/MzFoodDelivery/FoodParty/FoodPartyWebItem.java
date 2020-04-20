package ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.FoodParty;

import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Location;

import java.util.List;

class FoodPartyWebItem {
    public String id;
    public String name;
    public Location location;
    public String logo;
    public List<FoodPartyWebFoodItem> menu;
}
