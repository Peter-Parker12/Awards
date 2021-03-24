<%-- 
    Document   : index
    Created on : Feb 6, 2021, 9:11:23 AM
    Author     : megap
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Managing system</title>
    </head>
    <body>
        <% session.invalidate();
            String message = (String)request.getAttribute("message"); 
        if(message == null){
            message="";
        }
        %>
        <% String LoginAlert = (String)request.getAttribute("LoginAlert");
           if(LoginAlert != null){
        %>
        <script>
            alert("<%= LoginAlert %>")
        </script>
        <% } %>
        <h1>Welcome to FPTU's Library management system</h1>
        <p><%= message %></p>
        <a href="ServletController?action=LoginForm">Login</a>
        <a href="ServletController?action=RegisterForm">Register</a>
    </body>
</html>
