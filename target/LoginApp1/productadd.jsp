<%--
  Created by IntelliJ IDEA.
  User: chuva
  Date: 9/14/2023
  Time: 7:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Add Product</title>
</head>
<body>

<form action="/ProductServlet?action=insert" method="post">
    <table>
        <c:if test="${errMsg}">
            <tr>
                <td colspan="2">
                    <span style="color: red">${errMsg}</span>
                </td>
            </tr>
        </c:if>

        <tr>
            <td><label>Id</label></td>
            <td><input type="text" name="id" id="id"></td>
        </tr>

        <tr>
            <td><label>Product name</label></td>
            <td><input type="text" name="productName" id="productName"></td>
        </tr>

        <tr>
            <td><label>Quantity</label></td>
            <td><input type="number" name="quantity" value="quantity"></td>
        </tr>

        <tr>
            <td><label>Price</label></td>
            <td><input type="number" name="price" value="price"></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"></td>
            <td></td>
        </tr>
    </table>
</form>
</body>
</html>
