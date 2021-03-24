<%-- 
    Document   : landingPage
    Created on : Feb 8, 2021, 9:40:36 AM
    Author     : megap
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <h1>Welcome to FPTU's Book managing system</h1>
        
        <%  
            String username = (String)session.getAttribute("username");
        
         if(username == null){
            username="";
         }
        
        if(session != null){
        %>
        <p>Welcome  <%=username%></p>
        <p><a href="ServletController?action=LogOut">Logout</a></p>
        <ul>
            <li><a href="ServletController?action=booklist">See list of books by category name</a></li>
            <li><a href="ServletController?action=addbook">Add a new book</a></li>
            <li><a href="ServletController?action=addcategory">Add a new book category</a></li>
        </ul>
        <% }else { %>
        <p>Please <a href="Login.jsp">Login</a> to continue</p>
        <% } %> 
    </body>
</html>
