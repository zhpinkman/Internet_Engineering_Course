package ir.ac.ut.ie.CA_07_mzFoodDelivery.controllers;

import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.User.User;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.security.JWTAuthorizationFilter;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.utils.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/signup")
    public String signup(@RequestParam("email") String email, @RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,
                         @RequestParam("password") String password, final HttpServletResponse response) throws IOException {
        try {
            MzFoodDelivery.getInstance().addUser(email, firstName, lastName, password);
            return JWTAuthorizationFilter.getJWTToken(email);
        } catch (Exception e) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
        return null;
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, final HttpServletResponse response) throws IOException {
        try {
            User user = MzFoodDelivery.getInstance().loginUser(email, password);
            return JWTAuthorizationFilter.getJWTToken(user.getEmail());
        } catch (Exception e) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
        return null;
    }
}
