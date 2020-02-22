package controllers;

import schedulers.BackgroundJobManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TestService", urlPatterns = "/test")
public class TestService extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in in");
        String param = request.getParameter("status");
        if (param.equals("start")) {
            System.out.println("ali");
            BackgroundJobManager.startJob();
        } else if (param.equals("stop")) {
            System.out.println("shayan");
            BackgroundJobManager.stopJob();
        }
    }
}
