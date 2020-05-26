package ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.Restaurant;

import java.util.Comparator;

class SortByAveragePopularityDistance implements Comparator<Restaurant> {
    private Location location;

    SortByAveragePopularityDistance(Location location) {
        this.location = location;
    }

    @Override
    public int compare(Restaurant r1, Restaurant r2) {
//        double r1FoodsPopularityAverage = r1.getFoodsPopularityAverage();
//        double r2FoodsPopularityAverage = r2.getFoodsPopularityAverage();
//        double r1DistanceFromUser = r1.getDistanceFromLocation(location);
//        double r2DistanceFromUser = r2.getDistanceFromLocation(location);
//        int r1Rank = (int) (r1FoodsPopularityAverage / r1DistanceFromUser);
//        int r2Rank = (int) (r2FoodsPopularityAverage / r2DistanceFromUser);
//        return r1Rank - r2Rank;
        return 0;
    }
}
