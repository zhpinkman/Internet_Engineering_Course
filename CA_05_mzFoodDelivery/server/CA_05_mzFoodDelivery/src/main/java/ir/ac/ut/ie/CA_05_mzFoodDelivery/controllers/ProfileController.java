package ir.ac.ut.ie.CA_05_mzFoodDelivery.controllers;

import ir.ac.ut.ie.CA_05_mzFoodDelivery.controllers.Exceptions.ExceptionBadRequest;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.Delivery.Order;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.User.Cart;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.User.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String addToCart(@RequestBody(required = false) String restaurantId, @RequestBody(required = false) String foodName, @RequestBody(required = false) Integer amount) {
        System.out.println(restaurantId + " " + foodName + " " + amount);
        try {
            if (amount == null) {
                amount = 1;
                MzFoodDelivery.getInstance().addToCart(restaurantId, foodName);
            } else {
                MzFoodDelivery.getInstance().addToCart(restaurantId, foodName, amount.intValue());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new ExceptionBadRequest();
        }

        return "ok" + restaurantId + " " + foodName + " " + amount;
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
