<%-- 
    Document   : UpdateForm
    Created on : Feb 6, 2021, 8:25:55 PM
    Author     : megap
--%>

<%@page import="DTOS.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DTOS.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Form</title>
    </head>
    <body>
        <%  
            String action = (String)request.getAttribute("action");
            String message = (String)request.getAttribute("message");
            Book input = (Book)request.getAttribute("book");
        %>
        <h1><%= message %></h1>
        <form action = "ServletController?action=<%= action %>" method="POST">
            <table>
                <tr>
                    <td>Book ID: </td>
                    <td><input type="text" name="bookID" id="bookID" value="<%= input.getBookID() %>" readonly="true"></td>
                </tr>
                <tr>
                    <td>Book Name: </td>
                    <td><input type="text" name="bookName" id="bookName" value="<%= input.getBookName()%>"></td>
                </tr>
                <tr>
                    <td>Author: </td>
                    <td><input type="text" name="bookAuthor" id="Author" value="<%= input.getBookAuthor()%>"></td>
                </tr>
                <tr>
                    <td>Description: </td>
                    <td><textarea name="bookDescription" id="bookDescription" cols="30" rows="10"><%= input.getBookDescription()%></textarea></td>
                </tr>
                <tr>
                    <td>Publishing year: </td>
                    <td><input type="text" name="bookPublishYear" value="<%= input.getBookPublishingDate() %>"</td>
                </tr>
                <tr>
                    <td>Category: </td>
                    <td> <select name="Category" id="">
                        <% ArrayList<Category> categoryList = (ArrayList<Category>)request.getAttribute("Category");
                            for (Category category : categoryList) {%>
                                <option><%= category.getCategoryName() %></option>
                        <%}%>
                    </select></td>
                </tr>
                <tr>
                    <td>Status: </td>
                    <td><select name="status" id="" >
                        <option value="true">True</option>
                        <option value="false">False</option>
                    </select></td>
                </tr>
                <tr>
                    <td><button type="submit"><%= action %></button></td>
                </tr>
            </table>
        </form>
    </body>
</html>
