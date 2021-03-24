<%-- 
    Document   : Register
    Created on : Feb 8, 2021, 9:12:19 AM
    Author     : megap
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <% String usernameAlert = (String)request.getAttribute("usernameAlert");
           String passAlert = (String)request.getAttribute("passAlert");
           String nameAlert = (String)request.getAttribute("nameAlert");
           String repeatPassAlert = (String) request.getAttribute("repeatPassAlert");
           
           if(usernameAlert == null){
               usernameAlert = "";
           }
           if(passAlert == null){
               passAlert = "";
           }
           if(nameAlert == null){
               nameAlert = "";
           }
           if(repeatPassAlert == null){
               repeatPassAlert = "";
           }
           
        %>
        <form action="ServletController?action=register" method="POST">
            <table>
                <tr>
                    <td>Username: </td>
                    <td><input type="text" name="username" id=""></td>
                </tr>
                <tr>
                    <td> </td>
                    <td> <%= usernameAlert %> </td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="password" name="password" id=""></td>
                </tr>
                <tr>
                    <td> </td>
                    <td> <%= passAlert %> </td>
                </tr>
                <tr>
                    <td>Repeat password: </td>
                    <td><input type="password" name ="repeatPassword"</td>
                </tr>
                <tr>
                    <td></td>
                    <td> <%= repeatPassAlert %> </td>
                </tr>
                <tr>
                    <td>Full name: </td>
                    <td><input type="text" name="name" id=""></td>
                </tr>
                <tr>
                    <td> </td>
                    <td> <%= nameAlert %> </td>
                </tr>
                <tr>
                    <td>Already have an account?</td>
                    <td><a href="Login.jsp">Login now</a></td>
                </tr>
                <tr>
                    <td><button type="submit">Register</button></td>
                </tr>
            </table>
        </form>
    </body>
</html>
