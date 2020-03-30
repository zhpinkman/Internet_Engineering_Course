package controllers;

import MzFoodDelivery.MzFoodDelivery;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "ChargeCredit", urlPatterns = "/chargeCredit")
public class ChargeCredit extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double amount = Double.parseDouble(request.getParameter("credit"));
        MzFoodDelivery.getInstance().chargeUserCredit(amount);
        request.setAttribute("credit", amount);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("successCharge.jsp");
        requestDispatcher.forward(request, response);
    }
}
