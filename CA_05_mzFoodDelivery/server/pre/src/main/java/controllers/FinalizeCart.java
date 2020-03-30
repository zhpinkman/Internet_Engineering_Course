package controllers;

import MzFoodDelivery.MzFoodDelivery;
import schedulers.BackgroundJobManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "FinalizeCart", urlPatterns = "/finalize")
public class FinalizeCart extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String message = "";
        try {
            MzFoodDelivery.getInstance().finalizeOrder();
            message = "order successfully finalized!!";
            BackgroundJobManager.startJob();
        } catch (Exception e) {
            message = e.getMessage();
        }
        try {
            response.getOutputStream().println(message);
        } catch (IOException e) {
            e.printStackTrace();
            response.sendRedirect("http://localhost:8080/CA_03_mzFoodDelivery/cart");
        }
    }

}
