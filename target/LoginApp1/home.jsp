<%--
  Created by IntelliJ IDEA.
  User: teacher
  Date: 9/5/2023
  Time: 7:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home page</title>
</head>
<body>
    <h1>HELLO, <%=session.getAttribute("name").toString().toUpperCase()%></h1>
</body>
</html>
