<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users list</title>
    <style type="text/css">
        table {
            border-spacing: 1em .5em;
            padding: 0 2em 1em 0;
            border: 1px solid orange;
        }

        td {
            text-align: center;
            vertical-align: middle;
        }
    </style>
</head>
<body>
<h3>Users</h3>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Login</th>
        <th>Password</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.surname}</td>
            <td>${user.login}</td>
            <td>${user.password}</td>
            <td><a href="/internetshop_war_exploded/servlet/deleteUser?user_id=${user.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<hr>
<a href="registration">Register&#8594;</a>
</body>
</html>

