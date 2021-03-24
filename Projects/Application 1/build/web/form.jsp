<%-- 
    Document   : form
    Created on : Feb 6, 2021, 2:43:44 PM
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
        String idAlert = (String)request.getAttribute("idAlert");
        String nameAlert = (String)request.getAttribute("nameAlert");
        String authorAlert = (String)request.getAttribute("authorAlert");
        String desAlert = (String)request.getAttribute("desAlert");
        String categoryAlert = (String)request.getAttribute("categoryAlert");
        String publishDateAlert = (String)request.getAttribute("publishDateAlert");
        Book input = (Book) request.getAttribute("book"); 
        String isWrite = "";
        
        if(action.equals("Update")){
            isWrite = "readonly";
        }
        if(idAlert == null){
            idAlert="";
        } 
        if(nameAlert == null){
            nameAlert = "";
        } 
        if(authorAlert == null){
            authorAlert="";
        } 
        if(desAlert == null){
            desAlert="";
        } 
        if(categoryAlert == null){
            categoryAlert="";
        } 
        if(publishDateAlert == null){
            publishDateAlert="";
        }        
        %>
        <h1><%= message %></h1>
        <form action = "ServletController?action=<%= action %>" method="POST">
        <table>
            <tr>
                <td>Book ID: </td>
                <td><input type="text" name="bookID" id="bookID" value="<%= input.getBookID() %>" <%= isWrite %> ></td>
            </tr>
            <tr>
                <td></td>
                <td><%= idAlert %></td>
            </tr>
            <tr>
                <td>Book Name: </td>
                <td><input type="text" name="bookName" id="bookName" value="<%= input.getBookName()%>"></td>
            </tr>
            <tr>
                <td></td>
                <td><%= nameAlert %></td>
            </tr>
            <tr>
                <td>Author: </td>
                <td><input type="text" name="bookAuthor" id="Author" value="<%= input.getBookAuthor()%>"></td>
            </tr>
            <tr>
                <td></td>
                <td><%= authorAlert %></td>
            </tr>
            <tr>
                <td>Publishing year: </td>
                <td><input type="text" name="bookPublishYear" value="<%= input.getBookPublishingDate() %>"></td>
            </tr>
            <tr>
                <td></td>
                <td><%= publishDateAlert %></td>
            </tr>
            <tr>
                <td>Description: </td>
                <td><textarea name="bookDescription" id="bookDescription" cols="30" rows="10"><%= input.getBookDescription()%></textarea></td>
            </tr>
            <tr>
                <td></td>
                <td><%= desAlert %></td>
            </tr>
            <tr>
                <td>Category: </td>
                <td> <select name="Category" id="">
                    <option><%= input.getBookCategory() %></option>
                    <% ArrayList<Category> categoryList = (ArrayList<Category>)request.getAttribute("Category");
                    for (Category category : categoryList) {
                        if(!category.getCategoryName().equals(input.getBookCategory())){
                    %>
                    <option><%= category.getCategoryName() %></option>
                    <% }
                    }%>
                </select></td>
            </tr>
            <tr>
                <td></td>
                <td><%= categoryAlert %></td>
            </tr>
            <tr>
                <td>Status: </td>
                <td><select name="status" id="" >
                    <option><%= input.isBookStatus() %></option>
                    <option><% if(input.isBookStatus()){ %>
                    <%= "false" %>
                    <%} else{%>
                    <%= "true" %><%}%></option>
                </select></td>
            </tr>
            <tr>
                    <td><a href="ServletController?action=HomePage">Return to homepage</a></td>
            </tr>
            <tr>
                <td><button type="submit"><%= action %></button></td>
            </tr>
        </table>
        </form>
    </body>
</html>
