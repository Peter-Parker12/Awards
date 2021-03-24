<%-- 
    Document   : FormPage
    Created on : Mar 13, 2021, 3:42:36 PM
    Author     : megap
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Form Page</title>
    </head>
    <body>
        <h1>${requestScope.message}</h1>
        <form action="${action}" method="POST">
            <table>
                <tr>
                    <td>Laptop ID</td>
                    <td>:</td>
                    <td><input type="text" name="laptopID" value="${requestScope.laptop.latopID}" ${requestScope.readonly}></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td>${requestScope.idAlert}</td>
                </tr>
                <tr>
                    <td>Laptop Name</td>
                    <td>:</td>
                    <td><input type="text" name="laptopName" value="${requestScope.laptop.laptopName}"></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td>${requestScope.laptopNameAlert}</td>
                </tr>
                <tr>
                    <td>Technical Information</td>
                    <td>:</td>
                    <td><input type="text" name="technicalInfo" value="${requestScope.laptop.technicalInfo}"></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td>${requestScope.technicalInfoAlert}</td>
                </tr>
                <tr>
                    <td>Manufacturing year</td>
                    <td>:</td>
                    <td>
                        <select name="manufacturingYear">
                        <c:choose>
                            <c:when test="${empty requestScope.laptop.yearOfManufacture}" >
                                <option value=""></option>
                                <c:forEach var="year" begin="1990" end="2021">
                                    <option value="${year}">${year}</option>
                                </c:forEach>                       
                            </c:when>
                            <c:otherwise>
                                <option value="${requestScope.laptop.yearOfManufacture}">${requestScope.laptop.yearOfManufacture}</option>
                                    <c:forEach var="year" begin="1990" end="2021">
                                        <option value="${year}">${year}</option>
                                    </c:forEach>
                            </c:otherwise>  
                        </c:choose>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td>${requestScope.manufacturingAlert}</td>
                </tr>
                <tr>
                    <td>Producer</td>
                    <td>:</td>
                    <td><input type="text" name="producer" value="${requestScope.laptop.producer}"></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td>${producerAlert}</td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td>:</td>
                    <td>
                        <select name="status">
                            <c:choose>
                                <c:when test="${requestScope.laptop.status == 'true'}">
                                    <option value="true">True</option>
                                    <option value="false">False</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="false">False</option>
                                    <option value="true">True</option>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Supplier</td>
                    <td>:</td>
                    <td><select name="supplierID">
                            <c:choose>
                                <c:when test="${not empty requestScope.laptop.supplier.supplierName}">
                                    <option value="${requestScope.laptop.supplier.supplierID}">${requestScope.laptop.supplier.supplierName}</option>
                                    <c:forEach var="supplier" items="${requestScope.supplierList}">
                                        <c:choose><c:when test="${requestScope.laptop.supplier.supplierID != supplier.supplierID}">
                                            <option value="${supplier.supplierID}">${supplier.supplierName}</option>
                                        </c:when></c:choose>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="supplier" items="${requestScope.supplierList}">
                                        <option value="${supplier.supplierID}">${supplier.supplierName}</option>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                    </select></td>
                </tr>
                <tr>
                    <td><button typpe="submit">${action}</button></td>
                </tr>
            </table>
        </form>
        <a href="Homepage.jsp">Return to home page</a>
    </body>
</html>
