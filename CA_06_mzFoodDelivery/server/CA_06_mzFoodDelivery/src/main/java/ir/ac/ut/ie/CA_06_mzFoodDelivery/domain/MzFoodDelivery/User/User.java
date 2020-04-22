package ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.User;

import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Delivery.Order;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Food;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Location;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Restaurant;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.repository.MzRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User {
    private Cart userCart = new Cart();
    private Location location;
    private String firstName;
    private String lastName;
    private String email; // this field is used as id
    private double credit;
    private String phoneNumber;
    private List<Order> orderList = new ArrayList<Order>();

    public User(String firstName, String lastName, String email, String phoneNumber, Location location, double credit){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.credit = credit;
    }

    public void addToCart(CartItem cartItem) throws Exception {
        userCart.addToCart(cartItem);
    }

    public Cart getUserCart() {
        return userCart;
    }

    public Location getLocation() {
        return location;
    }

    public Order finalizeOrder() {
        Cart cart = cloneCart();
        userCart.emptyCart();
        return new Order(cart);
    }


//    todo kollan in order bayad avaz she chon qablesh clone mikardim
//     alan dg addToCart beshe too cart asli ezafe mishe asan nmikhad inkaro kard too hamoon order database ezafe mikonim

    public Cart cloneCart() {
//        Cart cart = new Cart();
//        for (CartItem cartItem: userCart.getCartItems()) {
//            try {
//                cart.addToCart(cartItem);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return cart;
        return null;
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
            System.out.println();
            MzRepository.getInstance().updateUser(this);
        }
        else
            throw new Exception("negative amount is not allowed");
    }

    public double getCartTotalPrice() throws SQLException {
        return userCart.getTotalPrice();
    }

    public void withdrawCredit(double amount) {
        credit -= amount;
    }

    public void deleteFromCart(String restaurantId, String foodName) throws Exception {
        userCart.delete(restaurantId, foodName);
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

    public Order getLatestOrder() {
        if (orderList.size() == 0) {
            return null;
        }
        return orderList.get(orderList.size() - 1);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
