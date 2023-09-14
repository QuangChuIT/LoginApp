<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Product list</title>
</head>
<body>
<div style="margin: auto; width: 60%;padding: 10px;">
    <div style="text-align: center">
        <h1>Product Manager</h1>
        <form action="/ProductServlet" method="post">
            <label>Search: </label>
            <input type="text" name="name" id="name">
            <input type="submit" value="Search">
        </form>
    </div>
    <c:if test="${errMsg != null}">
        <p style="color: red">${errMsg}</p>
    </c:if>
    <fmt:setLocale value="en_US"/>
    <div style="text-align: center">
        <table border="1">
            <thead>
            <tr>
                <th width="20px">ID</th>
                <th width="300px">Name</th>
                <th width="150px">Price</th>
                <th width="150">Quantity</th>
                <th width="120">Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td><fmt:formatNumber type="currency" value="${product.price}"/></td>
                    <td>${product.quantity}</td>
                    <td><a href="<c:url value="/ProductServlet?action=delete&id=${product.id}"/>">Delete</a> </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="<c:url value="/ProductServlet?action=create"/>">Add new</a>
    </div>
</div>

</body>
</html>
