<%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 27.01.2020
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="styleHeader.css">
    <title>Gardening</title>
</head>
<body>

<div class="header">
    <%
        HttpSession sessionG = request.getSession();
        sessionG.getMaxInactiveInterval();
        Object user = sessionG.getAttribute("user");

        if(user != null){
            user =  "Hi " +user;
        }else{
            user = "";
        }


    %>
    <div class = "container">
        <div class = "logo-container">
            <h1>Welcome to Our <span>Farm Supply</span></h1>
        </div>


        <ul class = "navigation">
            <a href="fertilizers.jsp"><li>Fertilizer</li></a>
            <a href="pesticides.jsp"><li>Pesticide</li></a>
            <a href="createAccount.jsp"><li>Sign up</li></a>
            <a href="login.jsp"><li>Login</li></a>
            <a><li><%=user%></li></a>
            <a href="showBasket.jsp"><li>Basket</li></a>


        </ul>

    </div>
</div>

</body>
</html>
