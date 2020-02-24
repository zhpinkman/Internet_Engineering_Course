package controllers;

import MzFoodDelivery.MzFoodDelivery;
import schedulers.ScheduledParty;
import schedulers.SecJob;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@WebServlet(name = "FoodParty", urlPatterns = "/foodParty")
public class FoodParty extends HttpServlet {
    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public void init() throws ServletException {
        try {
            new ScheduledParty().run();
            scheduler.scheduleAtFixedRate(new ScheduledParty(), 30, 10, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("foodparty.jsp");
        requestDispatcher.forward(request, response);
    }


    public void destroy() {
        scheduler.shutdown();
    }

}