package ir.ac.ut.ie.CA_07_mzFoodDelivery.controllers;

import com.google.gson.Gson;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.User.User;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.security.JWTAuthorizationFilter;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.utils.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/signup")
    public String signup(@RequestBody String jsonString, final HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(jsonString, Properties.class);
        String email = properties.getProperty("email");
        String firstName = properties.getProperty("firstName");
        String lastName = properties.getProperty("lastName");
        String password = properties.getProperty("password");
        try {
            MzFoodDelivery.getInstance().addUser(email, firstName, lastName, password);
            return JWTAuthorizationFilter.getJWTToken(email);
        } catch (Exception e) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
        return null;
    }

    @PostMapping("/login")
    public String login(@RequestBody String jsonString, final HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(jsonString, Properties.class);
        String email = properties.getProperty("email");
        String password = properties.getProperty("password");
        try {
            User user = MzFoodDelivery.getInstance().loginUser(email, password);
            return JWTAuthorizationFilter.getJWTToken(user.getEmail());
        } catch (Exception e) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
        return null;
    }

    @PostMapping("/glogin")
    public String glogin(@RequestBody String jsonString) throws GeneralSecurityException, IOException {
        System.out.println(jsonString);
        Gson gson = new Gson();
        Properties properties = gson.fromJson(jsonString, Properties.class);
        String jwtToken = properties.getProperty("token");
        String userEmail = JWTAuthorizationFilter.checkGoogleAuth(jwtToken);
        return JWTAuthorizationFilter.getJWTToken(userEmail);
    }
}
