package ir.ac.ut.ie.CA_08_mzFoodDelivery.controllers;

import com.google.gson.Gson;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.controllers.Exceptions.ExceptionBadCharacters;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.Delivery.Order;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.User.Cart;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.User.CartItem;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.User.User;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.utils.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping("/user")
public class ProfileController {


    @PostMapping("/charge")
    public User chargeCredit(@RequestBody String jsonString, final HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(jsonString, Properties.class);
        String amountString = properties.getProperty("amount");
        try {
            if(StringUtils.hasIllegalChars(amountString)){
                throw new ExceptionBadCharacters();
            }
            double amount = Double.parseDouble(amountString);
            MzFoodDelivery.getInstance().chargeUserCredit(amount);
            return MzFoodDelivery.getInstance().getUser();
        } catch (Exception e) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return null;
        }
    }

    @GetMapping("")
    public User getUser() {
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        try {
            return MzFoodDelivery.getInstance().getUser();
        } catch (Exception e) {
            return null;
        }
    }


    @PostMapping(path = "/cart", consumes = "application/json", produces = "application/json")
    public String addToCart(@RequestBody(required = true) String jsonString, final HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        try {
            Properties properties = gson.fromJson(jsonString, Properties.class);
            String restaurantId = properties.getProperty("restaurantId");
            String foodName = properties.getProperty("foodName");
            Integer amount = Integer.parseInt(properties.getProperty("amount"));
            if(StringUtils.hasIllegalChars(restaurantId + foodName)){
                throw new ExceptionBadCharacters();
            }
            MzFoodDelivery.getInstance().addToCart(restaurantId, foodName, amount);
            return Config.OK_RESPONSE;
        } catch (Exception e) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return null;
        }
    }


    @DeleteMapping("/cart")
    public String removeFromCart(@RequestBody String jsonString, final HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(jsonString, Properties.class);
        String restaurantId = StringUtils.stripTags(properties.getProperty("restaurantId"));
        String foodName = StringUtils.stripTags(properties.getProperty("foodName"));
        try {
            MzFoodDelivery.getInstance().deleteFromCart(restaurantId, foodName);
            return Config.OK_RESPONSE;
        } catch (Exception e) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return null;
        }
    }

    @GetMapping("/cart")
    public List<CartItem> getCart() throws SQLException {
        return MzFoodDelivery.getInstance().getCart();
    }


    @GetMapping("/orders")
    public List<Order> getOrders() throws SQLException {
        return MzFoodDelivery.getInstance().getOrders();
    }

    @PostMapping(path = "/orders")
    public String finalizeOrder(final HttpServletResponse response) throws IOException {
        try {
            MzFoodDelivery.getInstance().finalizeOrder();
            return Config.OK_RESPONSE;
        } catch (Exception e) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return null;
        }
    }
}
