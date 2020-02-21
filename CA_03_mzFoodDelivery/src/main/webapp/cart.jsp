<%@ page import="java.util.List" %>
<%@ page import="MzFoodDelivery.User.CartItem" %>
<%@ page import="MzFoodDelivery.MzFoodDelivery" %><%--
  Created by IntelliJ IDEA.
  User: zhivar
  Date: 2/20/20
  Time: 9:26 PM
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
    List<CartItem> cartItemList = MzFoodDelivery.getInstance().getCart().getCartItems();
%>
<%
    if (cartItemList.size() != 0) {
%>
<div><%=MzFoodDelivery.getInstance().getCart().getRestaurant().getName()%></div>
<ul>
    <%
        for (CartItem cartItem: cartItemList) {
    %>
    <li><%=cartItem.getFood().getName()%>: <%=cartItem.getQuantity()%></li>
    <%}%>
</ul>
<form action="" method="POST">
    <button type="submit">finalize</button>
</form>
<%} else {%>
    <span> user cart is empty</span>
<%}%>

</body>
</html>
