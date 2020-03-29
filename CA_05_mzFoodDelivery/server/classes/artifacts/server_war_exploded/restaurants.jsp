<%--
  Created by IntelliJ IDEA.
  User: zhivar
  Date: 2/20/20
  Time: 5:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="MzFoodDelivery.MzFoodDelivery"  %>
<%@ page import="MzFoodDelivery.Restaurant.Restaurant" %>
<%@ page import="MzFoodDelivery.Restaurant.Location" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="MzFoodDelivery.Restaurant.Food" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Restaurants</title>
    <style>
        table {
            text-align: center;
            margin: auto;
        }
        th, td {
            padding: 5px;
            text-align: center;
        }
        .logo{
            width: 100px;
            height: 100px;
        }
    </style>
</head>
<body>
<%
    List<Restaurant> restaurantList = MzFoodDelivery.getInstance().getNearRestaurants();
%>
<table>
    <tr>
        <th>id</th>
        <th>logo</th>
        <th>name</th>
        <th>description</th>
        <th>average delivery time</th>
    </tr>
    <%
        for (Restaurant restaurant: restaurantList) {
    %>
    <tr>
        <td>
            <a href="restaurant.jsp?restaurantId=<%=restaurant.getId()%>">
                <%=restaurant.getId()%>
            </a>
        </td>
        <td><img class="logo" src=<%=restaurant.getLogo()%> alt="logo"></td>
        <td><%=restaurant.getName()%></td>
        <td><%=restaurant.getPropertyOrDefaultValue("description", "no description")%></td>
        <td><%=restaurant.doublePrecision(restaurant.getAverageTimeToDeliver())%> seconds</td>
    </tr>
    <%}%>
</table>
</body>
</html>