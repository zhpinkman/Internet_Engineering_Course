package ir.ac.ut.ie.CA_05_mzFoodDelivery.controllers;

import com.google.gson.Gson;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.controllers.Exceptions.ExceptionBadRequest;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.Delivery.Order;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.User.Cart;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.User.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Properties;

class ChargeAmount {
    public double amount;
}

@RestController
@RequestMapping("/user")
public class ProfileController {


    @PostMapping("/charge")
    public User chargeCredit(@RequestBody ChargeAmount chargeAmount) {
        System.out.println(chargeAmount);
        MzFoodDelivery.getInstance().chargeUserCredit(chargeAmount.amount);
        return MzFoodDelivery.getInstance().getUser();
    }

    @GetMapping("")
    public User getUser() {
        return MzFoodDelivery.getInstance().getUser();
    }


    @PostMapping(path = "/cart", consumes = "application/json", produces = "application/json")
    public String addToCart(@RequestBody(required = true) String jsonString) {
        Gson gson = new Gson();
        try {
            Properties properties = gson.fromJson(jsonString, Properties.class);
            String restaurantId = properties.getProperty("restaurantId");
            String foodName = properties.getProperty("foodName");
            Integer amount = Integer.parseInt(properties.getProperty("amount"));
            MzFoodDelivery.getInstance().addToCart(restaurantId, foodName, amount);
            return "ok";

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
//            throw new ExceptionBadRequest();
        }
    }

    @GetMapping("/cart")
    public Cart getCart() {
        return MzFoodDelivery.getInstance().getCart();
    }


    @GetMapping("/orders")
    public List<Order> getOrders() {
        return MzFoodDelivery.getInstance().getOrders();
    }

}
