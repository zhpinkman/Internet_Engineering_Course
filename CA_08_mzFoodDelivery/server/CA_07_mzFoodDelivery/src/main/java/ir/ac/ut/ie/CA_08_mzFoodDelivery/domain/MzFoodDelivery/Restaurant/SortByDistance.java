package ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.Restaurant;

import java.util.Comparator;

class SortByDistance implements Comparator<Restaurant> {
    private Location location;

    SortByDistance(Location location) {
        this.location = location;
    }

    @Override
    public int compare(Restaurant r1, Restaurant r2) {
        return (int) r1.getDistanceFromLocation(location) - (int) r2.getDistanceFromLocation(location);
    }
}
