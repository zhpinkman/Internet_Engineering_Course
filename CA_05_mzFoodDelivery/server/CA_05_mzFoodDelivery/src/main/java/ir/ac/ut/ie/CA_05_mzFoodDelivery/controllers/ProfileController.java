package ir.ac.ut.ie.CA_05_mzFoodDelivery.controllers;

import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.User.User;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class ProfileController {



    @PostMapping("/charge")
    public String chargeCredit(@RequestBody double amount) {
        System.out.println(amount);
        MzFoodDelivery.getInstance().chargeUserCredit(amount);
        return "user credit charged successfully";
    }

    @GetMapping("")
    public User getUser() {
        return MzFoodDelivery.getInstance().getUser();
    }


    @PostMapping("addToCart")
    public void addToCart(@RequestParam String restaurantId, @RequestParam String foodName) {
        System.out.println(restaurantId + " " + foodName);
    }

}
