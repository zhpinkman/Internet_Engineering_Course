package ir.ac.ut.ie.CA_05_mzFoodDelivery.controllers;


import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class testController {


    @PostMapping("/login")
    public void login(@RequestBody Map<String, Object> userMap) {
        System.out.println(userMap.get("firstName"));
        System.out.println(userMap.get("zhivar"));
    }

}
