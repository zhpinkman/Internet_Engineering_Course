package ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.User;

import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Delivery.Order;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Food;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Location;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Cart userCart = new Cart();
    private Location location = new Location(0, 0);
    private String firstName;
    private String lastName;
    private String email; // this field is used as id
    private double credit;
    private String phoneNumber;
    private List<Order> orderList = new ArrayList<Order>();

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

    public Order finalizeOrder() {
        Cart cart = cloneCart();
        userCart.emptyCart();
        return new Order(cart);
    }


    public Cart cloneCart() {
        Cart cart = new Cart();
        for (CartItem cartItem: userCart.getCartItems()) {
            try {
                cart.addToCart(cartItem);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cart;
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


    public void chargeUserCredit(double amount) throws Exception {
        if (amount > 0)
            credit += amount;
        else
            throw new Exception("negative amount is not allowed");
    }

    public double getCartTotalPrice() {
        return userCart.getTotalPrice();
    }

    public void withdrawCredit(double amount) {
        credit -= amount;
    }

    public void deleteFromCart(Restaurant restaurant, Food food) throws Exception {
        userCart.delete(restaurant, food);
    }

    public Order getOrderById(double id) throws Exception {
        for (Order order : orderList) {
            if (order.getId() == id) {
                return order;
            }
        }
        throw new Exception("orderId not found");
    }

    public List<Order> getOrders() {
        return orderList;
    }

    public void addOrder(Order order) {
        orderList.add(order);
    }

    public Order getLatestOrder() throws Exception {
        if (orderList.size() == 0) {
            throw new Exception("no order for this user");
        }
        return orderList.get(orderList.size() - 1);
    }
}
