package ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.User;

import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Delivery.Order;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Delivery.OrderItem;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Food;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Location;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Restaurant;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.MzRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User {
    public static final String userEmail = "ekhamespanah@yahoo.com";
    private Cart userCart = new Cart();
    private Location location;
    private String firstName;
    private String lastName;
    private String email; // this field is used as id
    private double credit;
    private int orders;
    private String phoneNumber;

    public User(String firstName, String lastName, String email, String phoneNumber, Location location, double credit, int orders){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.credit = credit;
        this.orders = orders;
    }

    public int getNumOfOrders() {
        return orders;
    }

    public void addToCart(CartItem cartItem) throws Exception {
        userCart.addToCart(cartItem);
    }

    public List<CartItem> getUserCart() throws SQLException {
        List<CartItem> userCart = MzRepository.getInstance().getUserCart(userEmail);
        for (CartItem cartItem: userCart) {
            Food food = MzRepository.getInstance().getFood(cartItem.getRestaurantId(), cartItem.getFoodName());
            cartItem.setUnitPrice(food.getPrice());
        }
        return userCart;
    }

    public Location getLocation() {
        return location;
    }

    public List<CartItem> finalizeOrder() throws SQLException {
        List<CartItem> cartItems = addOrder();
        userCart.emptyCart();
        return cartItems;
    }


    public List<CartItem> addOrder() throws SQLException {
        User user = MzRepository.getInstance().getUser(userEmail);
        increaseUserOrders();
        List<CartItem> cartItems = user.getUserCart();
        for (CartItem cartItem: cartItems) {
            Food food = MzRepository.getInstance().getFood(cartItem.getRestaurantId(), cartItem.getFoodName());
            MzRepository.getInstance().addOrderItem(new OrderItem(userEmail, orders, cartItem, food.getPrice()));
        }
        Order userOrder = new Order(userEmail, orders);
        MzRepository.getInstance().addUserOrder(userOrder);
        return cartItems;
    }

    private void increaseUserOrders() throws SQLException {
        orders += 1;
        MzRepository.getInstance().updateUser(this);
    }

    public int getUserCartSize() throws SQLException {
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


    public void chargeUserCredit(double amount) throws Exception {
        if (amount > 0) {
            credit += amount;
//            System.out.println();
            MzRepository.getInstance().updateUser(this);
        }
        else
            throw new Exception("negative amount is not allowed");
    }

    public double getCartTotalPrice() throws SQLException {
        return userCart.getTotalPrice();
    }

    public void withdrawCredit(double amount) throws SQLException {
        credit -= amount;
        MzRepository.getInstance().updateUser(this);
    }

    public void deleteFromCart(String restaurantId, String foodName) throws Exception {
        CartItem cartItem = MzRepository.getInstance().findCartItem(new CartItem(userEmail, restaurantId, foodName));
        if (cartItem.getQuantity() != 1)
            cartItem.decreaseQuantity(1);
        else if (cartItem.getQuantity() == 1)
            MzRepository.getInstance().removeCartItem(cartItem);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
