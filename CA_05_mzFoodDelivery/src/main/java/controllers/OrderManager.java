package controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "OrderManager", urlPatterns = "/orders")
public class OrderManager extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("order.jsp");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            response.getOutputStream().println(e.getMessage());
        }
    }

}
