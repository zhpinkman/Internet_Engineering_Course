<%@ page import="MzFoodDelivery.Order" %>
<%@ page import="MzFoodDelivery.MzFoodDelivery" %>
<%@ page import="MzFoodDelivery.User.CartItem" %>
<%@ page import="MzFoodDelivery.Status" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="java.time.Duration" %>
<%--
  Created by IntelliJ IDEA.
  User: zhivar
  Date: 2/22/20
  Time: 2:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User</title>
    <style>
        li, div, form {
            padding: 5px
        }
    </style>
</head>
<body>

<%
    String orderId = request.getParameter("orderId");
    Order order = null;
    try {
        order = MzFoodDelivery.getInstance().getOrderById(Double.parseDouble(orderId));
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
<%
    if (order == null) {
%>
<div>
    order not found!!!
</div>
<%
    } else {
%>
<div><%=order.getCart().getRestaurant().getName()%></div>
<ul>
    <%
        for (CartItem cartItem: order.getCart().getCartItems()) {
    %>
    <li>
        <%=cartItem.getFood().getName()%>
        ,
        <%=cartItem.getQuantity()%>
    </li>
    <%}%>
</ul>
<!-- One of these states -->
<%
    if (order.getStatus() == Status.SEARCHING) {
%>
<div>
    status : finding delivery
</div>
<%}%>
<!-- or -->

<%
    if (order.getStatus() == Status.DELIVERING) {
%>
<div>
    <div>status : delivering</div>
    <%
        Duration duration = order.getRemainingArrivingTime();
    %>
    <%=order.getStartingDeliveryTime().getMinute()%>
    <%=order.getStartingDeliveryTime().getSecond()%>
    <div>remained time : <%=duration.getSeconds() / 60%> min <%=duration.getSeconds() % 60%> sec</div>
</div>
<%}%>
<!-- or -->

<%
    if (order.getStatus() == Status.DELIVERED) {
%>
<div>
    status : done
</div>
<%}%>
<%}%>

</body>
</html>