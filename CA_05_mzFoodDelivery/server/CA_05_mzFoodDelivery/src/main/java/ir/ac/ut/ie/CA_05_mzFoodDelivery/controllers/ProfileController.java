package ir.ac.ut.ie.CA_05_mzFoodDelivery.controllers;

import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class ProfileController {



    @PostMapping("/charge")
    public String chargeCredit(@RequestBody double amount) {
        System.out.println(amount);
        MzFoodDelivery.getInstance().chargeUserCredit(amount);
        return "user credit charged successfully";
    }
}
