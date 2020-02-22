<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="MzFoodDelivery.MzFoodDelivery"  %>
<%@ page import="MzFoodDelivery.Restaurant.Restaurant" %>
<%@ page import="java.util.List" %>
<%@ page import="MzFoodDelivery.Restaurant.Food" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Restaurant</title>
    <style>
        img {
            width: 50px;
            height: 50px;
        }
        li {
            display: flex;
            flex-direction: row;
            padding: 0 0 5px;
        }
        div, form {
            padding: 0 5px
        }
    </style>
</head>
<body>
<%
    Restaurant restaurant = MzFoodDelivery.getInstance().getNearRestaurantById(request.getParameter("restaurantId"));
%>
<ul>
    <li><%=restaurant.getId()%></li>
    <li>name: <%=restaurant.getName()%> </li>
    <li>location: (<%=restaurant.getLocation().getX()%>, <%=restaurant.getLocation().getY()%>)</li>
    <li>logo: <img src=<%=restaurant.getLogo()%> alt="logo"></li>

    <!-- IN CASE YOU WANT SOME BONUS : -->
    <!-- <li>estimated delivery time: 10 min 2 sec </li> -->

    <%
        List<Food> foodList = restaurant.getMenu();
    %>

    <li>menu:
        <ul>
<%--            <%=foodList.get(0).getName()%>--%>
            <%
                for (Food food: foodList) {
            %>
            <li>
                <img src=<%=food.getImage()%> alt="logo">
                <%
                    System.out.println(food.getName());
                %>
                <div> <%=food.getName()%> </div>
                <div><%=food.getPrice()%> Toman</div>
                <form action="addToCart?restaurantId=<%=restaurant.getId()%>&foodName=<%=food.getName()%>" method="POST">
                    <button type="submit">addToCart</button>
                </form>
            </li>
            <%}%>
        </ul>
    </li>
</ul>
</body>
</html>