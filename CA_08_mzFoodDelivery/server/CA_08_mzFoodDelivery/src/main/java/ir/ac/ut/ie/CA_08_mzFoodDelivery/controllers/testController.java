package ir.ac.ut.ie.CA_08_mzFoodDelivery.controllers;


import com.google.gson.Gson;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.Delivery.Order;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.User.Cart;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class testController {

    @GetMapping("/version")
    public String version() {
        return "1.00";
    }

    @GetMapping("")
    public String testing() {
        return "something";
    }


    @PostMapping("/login")
    public void login(@RequestBody Map<String, Object> userMap) {
//        System.out.println(userMap.get("firstName"));
//        System.out.println(userMap.get("zhivar"));
    }

    @GetMapping("/cart")
    public Cart getCart() {
        return new Cart();
    }

    @GetMapping("/order")
    public String getOrder() {
        Order order  = new Order();
        Gson gson = new Gson();
        return gson.toJson(order);
    }

    @DeleteMapping("")
    public void testDelete(@RequestBody String jsonString) {
        System.out.println(jsonString);
    }

}

