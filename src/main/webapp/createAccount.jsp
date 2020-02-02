<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 27.01.2020
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Create Account</title>
</head>
<body>
<%@include file="header.jsp"%>
<%
    Object errMsg = request.getAttribute("errMsg");
    String userErrMsg = "";
    if(errMsg != null){
        userErrMsg += errMsg;
    }
    String login = "";
    if(login != null){
        login = (String) request.getAttribute("user");
    }

    Object pErrMsg = request.getAttribute("pErrMsg");
    String passErrMsg = "";
    if(pErrMsg != null){
        passErrMsg += pErrMsg;
    }

    Object password = "";
    if(password != null){
        password = request.getAttribute("password");
    }

%>

<c:set var = "loginValue" value = "<%=login%>"/>

<h1>Create new account: </h1>

<form action="newLoginValidator" method = "post">
    <br>
    Choose your login:<br>
    <input type = "text" class="textBox" name = "user" value = "${loginValue}">
    <p style="color: red"><%=userErrMsg%></p><br><br>

    Choose your password:
    <input type = "password" name = "password" value = "">
    <p style = "color: red"><%=passErrMsg%></p><br>
    <input type = "submit" value="create">


</form>
</body>
</html>
