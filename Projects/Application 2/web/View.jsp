<%-- 
    Document   : View
    Created on : Mar 13, 2021, 10:08:28 AM
    Author     : megap
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Page</title>
    </head>
    <body>
        
        <h1>View list of computers</h1>
        <form action="LoadList" method="POST">
        <table>
            <tr>
                <td>Supplier:</td>
                <td><select name="supplierID">
                    <c:forEach var="supplier" items="${requestScope.supplierList}">
                        <option value="${supplier.supplierID}">${supplier.supplierName}</option>
                    </c:forEach>
                    </select></td>
                    <td><button type="submit">Search</button></td>
            </tr>              
                
        </table>
        </form>
        <c:choose>
            <c:when test="${empty requestScope.computerList}">
                <h2>There is no product belongs to the current suppliers</h2>
            </c:when>
            <c:otherwise>    
            <table border="1">                   
                <thead>
                    <tr>
                        <th>LaptopID</th>
                        <th>Laptop Name</th>
                        <th>Technical Information</th>
                        <th>Manufacturing Year</th>
                        <th>Producer</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="computerItem" items="${requestScope.computerList}"> 
                        <tr>
                            <td>${computerItem.latopID}</td>
                            <td>${computerItem.laptopName}</td>
                            <td>${computerItem.technicalInfo}</td>
                            <td>${computerItem.yearOfManufacture}</td>
                            <td>${computerItem.producer}</td>
                            <td>${computerItem.status}</td>
                            <td><c:url var="formpage" value="FormPage">
                                    <c:param name="id" value="${computerItem.latopID}"></c:param>
                                </c:url> <a href="${formpage}">Update</a>
                            </td>
                            <td><c:url var="delete" value="Delete">
                                    <c:param name="id" value="${computerItem.latopID}"></c:param>
                                </c:url> <a onclick="return confirm('Do you really want to delete the book?')" href="${delete}" >Delete</a>
                            </td>
                            
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            </c:otherwise>
        </c:choose>
    <a href="Homepage.jsp">Return to home page</a>
    </body>
</html>
