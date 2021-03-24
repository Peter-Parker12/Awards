<%-- 
    Document   : Login
    Created on : Feb 6, 2021, 9:06:23 AM
    Author     : megap
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <%  
            String message = (String)request.getAttribute("loginAlert");
               
           if(message == null){
               message = "";
           }
        %>
        <form action="ServletController?action=Login" method = "POST">
            <table>
                <tr>
                    <td>Username: </td>
                    <td><input type="text" name="Username" id="Username"></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="password" name="Password" id="Password"></td>
                </tr>
                <tr>
                    <td>  </td>
                    <td><%= message %></td>
                </tr>
                <tr>
                    <td><a href="Register.jsp">Register an account</a></td>
                </tr>
                <tr>
                    <td><button type="submit">Login</button></td>
                </tr>
            </table>
        </form>
    </body>
</html>
