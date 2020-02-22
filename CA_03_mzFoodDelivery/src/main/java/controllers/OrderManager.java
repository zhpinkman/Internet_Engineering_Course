package controllers;

import HTTPRequestHandler.HTTPRequestHandler;
import MzFoodDelivery.MzFoodDelivery;
import MzFoodDelivery.Restaurant.Restaurant;
import MzFoodDelivery.Delivery;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "OrderManager", urlPatterns = "/orderManager")
public class OrderManager extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
//            importDeliveriesFromWeb();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

}
