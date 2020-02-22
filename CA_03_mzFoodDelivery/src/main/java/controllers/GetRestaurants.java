package controllers;

import HTTPRequestHandler.HTTPRequestHandler;
import MzFoodDelivery.MzFoodDelivery;
import MzFoodDelivery.Restaurant.Restaurant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "GetRestaurants", urlPatterns = "/getRestaurants")
public class GetRestaurants extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            importRestaurantsFromWeb();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("restaurants.jsp");
        requestDispatcher.forward(request, response);
    }

    public void importRestaurantsFromWeb() throws Exception {
        String RestaurantsJsonString = HTTPRequestHandler.getRequest("http://138.197.181.131:8080/restaurants");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Restaurant> restaurants = gson.fromJson(RestaurantsJsonString, new TypeToken<List<Restaurant>>() {
        }.getType());
        int counter = 1;
        for (Restaurant restaurant : restaurants) {
//            System.out.println(counter + "----------------");
            counter++;
//            restaurant.print();
            try {
                MzFoodDelivery.getInstance().addRestaurant(restaurant);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}


