package ir.ac.ut.ie.CA_05_mzFoodDelivery.controllers;

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


    @PostMapping(path = "/cart", produces = "application/json")
    public void addToCart(@RequestParam(name = "restaurantId") String restaurantId, @RequestParam String foodName, @RequestParam(required = false, defaultValue="1") int amount) {
        System.out.println(restaurantId + " " + foodName + " " + amount);
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
