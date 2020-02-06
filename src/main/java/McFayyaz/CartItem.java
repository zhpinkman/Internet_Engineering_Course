package McFayyaz;

public class CartItem {
    private Restaurant restaurant;
    private Food food;

    public CartItem(Restaurant restaurant, Food food) {
        this.restaurant = restaurant;
        this.food = food;
    }

    public Food getFood() {
        return food;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

}
