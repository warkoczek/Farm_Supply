<%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 27.01.2020
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
</head>
<body>
<%@include file="header.jsp"%>

<%
    Object userTry = request.getAttribute("user");
    Object errMsg = request.getAttribute("errMsg");
    if(errMsg != null){
        System.out.println(errMsg.toString());
    }

%>
<h1>Please login</h1>
<form action="loginValidator" method="post">
    <br><br>
    Your login:<br><br>
    <input type = "text" name = "user" value = ""><br>
    <p style="color: red">
        <%
            if(request.getAttribute("errMsg") != null){
                out.print(request.getAttribute("errMsg"));
            }

        %>
    </p>
    <link href="createAccount.jsp">
    <br><br>
    Your password:<br><br>
    <input type = "password" name = "password" value="" ><br><br>
    <input type = "submit" value = "Log in">
</form>
</body>
</html>
