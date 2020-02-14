package MzFoodDelivery.User;

import MzFoodDelivery.Restaurant.Location;
import MzFoodDelivery.Restaurant.Restaurant;

public class User {
    private Cart userCart = new Cart();
    private Location location = new Location(0, 0);
    private String firstName;
    private String lastName;
    private String email;
    private double credit;
    private String phoneNumber;

    public User(String firstName, String lastName, String email, String phoneNumber, Location location){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.credit = 0;
    }

    public void addToCart(CartItem cartItem) throws Exception {
        userCart.addToCart(cartItem);
    }

    public Cart getUserCart() {
        return userCart;
    }

    public Restaurant getActiveRestaurant() {
        return userCart.getRestaurant();
    }

    public Location getLocation() {
        return location;
    }

    public void finalizeOrder() {
        userCart.emptyCart();
    }

    public int getUserCartSize() {
        return userCart.getSize();
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

    public String getEmail(){
        return email;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public double getCredit(){
        return credit;
    }


    public void chargeUserCredit(double amount) {
        if (amount > 0)
            credit += amount;
    }
}
