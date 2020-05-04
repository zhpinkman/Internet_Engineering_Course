package ir.ac.ut.ie.CA_07_mzFoodDelivery.controllers;

import ir.ac.ut.ie.CA_07_mzFoodDelivery.security.JWTAuthorizationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/signup")
    public String signup(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email) {
        String token = JWTAuthorizationFilter.getJWTToken(username);
        return token;
    }
}
