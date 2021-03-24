<%-- 
    Document   : listPage
    Created on : Feb 6, 2021, 10:43:13 AM
    Author     : megap
--%>

<%@page import="DTOS.Category"%>
<%@page import="DTOS.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book List </title>
    </head>
    <body>
        
        <h1>Welcome to FPTU Library's book list</h1>
        <p>Please choose the category that you want to look</p>
        <% ArrayList<Category> categoryList = (ArrayList<Category>) request.getAttribute("categoryList"); 
        %>
        <form action="ServletController?action=categoryView" method="POST">
            <table>
                <tr>
                    <td>
                        <select name="category" id="">
                            <option></option>
                            <% for (Category category : categoryList) { %>
                                <option><%= category.getCategoryName() %></option>
                            <% } %>
                        </select>
                    </td>
                    <td><button type="submit">Search</button></td>
                </tr>
            </table>
        </form>    
        <table border="1">
            <tr>
                <td>Book ID</td>
                <td>Book Name</td>
                <td>Author</td>
                <td>Description</td>
                <td>Publishing Year</td>
                <td>Category</td>
                <td>Status</td>
            </tr>
            <% ArrayList<Book> bookList = (ArrayList<Book>)request.getAttribute("categoryName");
            if(bookList != null){
                for (Book book : bookList) {
            %>
            <tr>
                <td><%= book.getBookID() %></td>
                <td><%= book.getBookName() %></td>
                <td><%= book.getBookAuthor()%></td>
                <td><%= book.getBookDescription()%></td>
                <td><%= book.getBookPublishingDate()%></td>
                <td><%= book.getBookCategory()%></td>
                <td><%= book.isBookStatus() %></td>
                <td><a href="ServletController?action=updatebook&bookID=<%= book.getBookID() %>">Edit</a></td>
                <td><a onclick="return confirmation()" href="ServletController?action=deletebook&bookID=<%= book.getBookID() %>" >Delete</a></td>
            </tr>
            <% }
            }%>
        </table>
        
        <p><a href="ServletController?action=HomePage">Return to Homepage</a></p>
        <script>
            function confirmation(){
                var r = confirm("Do you really want to delete the book?")
                return r;
            }
        </script>
    </body>
</html>
