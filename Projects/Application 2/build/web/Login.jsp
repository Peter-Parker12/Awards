<%-- 
    Document   : Login
    Created on : Mar 13, 2021, 9:43:29 AM
    Author     : megap
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="Login" method="POST">
            <table>
                <tr>
                    <td>Username</td>
                    <td>:</td>
                    <td><input type="text" name="username" value="${requestScope.username}"></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td>:</td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td>${requestScope.userAlert}</td>
                </tr>
                <tr>
                    <td><button type="submit">Login</button></td>
                </tr>
            </table>
        </form>
    </body>
</html>
