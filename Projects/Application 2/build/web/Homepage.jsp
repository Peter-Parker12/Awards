<%-- 
    Document   : Homepage
    Created on : Mar 13, 2021, 9:57:28 AM
    Author     : megap
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <h1>Computer management system</h1>
        <p>Welcome ${sessionScope.user.fullName}</p>
        <ul>
            <li><a href="Logout">Logout</a></li>
            <li><a href="FormPage">Add a new computer</a></li>
            <li><a href="LoadList">View a list of computers</a></li>
        </ul>
    </body>
</html>
