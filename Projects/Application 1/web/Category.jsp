<%-- 
    Document   : Category
    Created on : Feb 12, 2021, 9:16:50 AM
    Author     : megap
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category</title>
    </head>
    <body>
        <%  
            String categoryID=(String)request.getAttribute("categoryID");
            String categoryName = (String)request.getAttribute("categoryName");
            String categoryDes = (String)request.getAttribute("categoryDescription");
            String IDAlert = (String)request.getAttribute("IDAlert");
            String nameAlert = (String)request.getAttribute("nameAlert");
            String desAlert = (String)request.getAttribute("descriptionAlert");
            if(IDAlert == null){
                IDAlert="";
            }
            if(nameAlert == null){
                nameAlert="";
            }
            if(desAlert == null){
                desAlert="";
            }
            if(categoryID == null){
                categoryID="";
            }
            if(categoryDes==null){
                categoryDes="";
            }
            if(categoryName==null){
                categoryName="";
            }
            if(session != null){
        %>
        <form action="ServletController?action=addNewCategory" method="POST">
            <table>
                <tr>
                    <td>Category ID: </td>
                    <td><input type="text" name="categoryID" id="" value="<%= categoryID %>"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><%= IDAlert %></td>
                </tr>
                <tr>
                    <td>Category name: </td>
                    <td><input type="text" name="categoryName" id="" value="<%= categoryName %>"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><%= nameAlert %></td>
                </tr>
                <tr>
                    <td>Description: </td>
                    <td><textarea name="categoryDescription" id="" cols="30" rows="10"><%= categoryDes %></textarea></td>
                </tr>
                <tr>
                        <td></td>
                        <td><%= desAlert %></td>
                </tr>
                <tr>
                    <td><a href="ServletController?action=HomePage">Return to homepage</a></td>
                </tr>
                <tr>
                    <td><button type="submit">Add</button></td>
                </tr>
            </table>
        </form>
        <% }else { %>
        <p>Please <a href="Login.jsp">Login</a> to continue</p>
        <% } %> 
    </body>
</html>
