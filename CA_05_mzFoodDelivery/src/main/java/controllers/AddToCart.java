package controllers;

import MzFoodDelivery.MzFoodDelivery;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "AddToCart", urlPatterns = "/addToCart")
public class AddToCart extends HttpServlet {


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String restaurantId = request.getParameter("restaurantId");
        String foodName = request.getParameter("foodName");

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println(restaurantId);
        out.println(foodName);



        try {
            MzFoodDelivery.getInstance().addToCart(MzFoodDelivery.getInstance().getNearRestaurantById(restaurantId).getName(), foodName);
            response.setStatus(200);
            response.getWriter().println("Success");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(400, e.getMessage());
        }
//        response.sendRedirect(request.getContextPath() + "/restaurant.jsp?restaurantId=" + restaurantId);

    }
}
